import { useContext, useEffect, useState } from "react";
import { LaboratoryReportDTO, LaboratoryReportRequest, UserDropdownDTO } from "../../api";
import { ClientsContext } from "../../store/ClientsContext";
import LaboratoryReportTableComponent from "./LaboratoryReportTableComponent";
import { Box, Button, FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../store/UserContext";
import * as Yup from 'yup';

function LaboratoryReportTableState() {
    const [laboratoryReports, setLaboratoryReports] = useState<Array<LaboratoryReportDTO>>([]);
    const [userDropdown, setUserDropdown] = useState<Array<UserDropdownDTO>>([]);
    const [newRow, setNewRow] = useState<LaboratoryReportRequest | undefined>();
    const [isNewRow, setIsNewRow] = useState<boolean>(false);
    const [currentEditingIndex, setCurrentEditingIndex] = useState<number | undefined>(undefined);
    const { laboratoryReportClient, userClient } = useContext(ClientsContext);
    const { user } = useContext(UserContext);
    const [errors, setErrors] = useState({});
    const schema = Yup.object().shape({
        date: Yup.date().required('Date is required'),
        userId: Yup.string().required('User id is required'),
        description: Yup.string()
        .required("Description is required")
        .min(10, "Description must be at least 10 characters")
        .test("sql-injection", "Description contains invalid characters", (value) => {
            // Disallowing specific special characters
            const forbiddenCharactersRegex = /[<>!@#$%^&*]/;
            if (forbiddenCharactersRegex.test(value)) {
                return false;
            }
            // Ensuring the description starts with a letter
            const startsWithLetterRegex = /^[A-Za-z]/;
            if (!startsWithLetterRegex.test(value)) {
                return false;
            }
            // Basic check for potential SQL injection characters
            const forbiddenCharacters = /[-';]/;
            return !forbiddenCharacters.test(value);
        }).test("advanced-validation", "Description contains forbidden keywords", (value) => {
                const forbiddenKeywords = ["drop", "truncate", "delete"];
                for (const keyword of forbiddenKeywords) {
                    if (value.toLowerCase().includes(keyword)) {
                        return false;
                    }
                }
                return true;
            }),
    });

    const navigate = useNavigate();

    function onUpdate(index: number) {
        setCurrentEditingIndex(index);
        setNewRow({
            id: laboratoryReports[index].id,
            date: laboratoryReports[index].date,
            description: laboratoryReports[index].description,
            userId: laboratoryReports[index].userId
        });
    }

    function cancel() {
        setIsNewRow(false);
        setNewRow(undefined);
        setCurrentEditingIndex(undefined);
        setErrors({});
    }

    async function deleteRow(id: number) {
        await laboratoryReportClient.deleteReport({ id: id });
        var values: Array<LaboratoryReportDTO> = [];
        laboratoryReports.forEach(elem => {
            if (elem.id !== id)
                values.push(elem);
        });
        setLaboratoryReports([...values]);
    }

    async function submitNewRow() {
        try {
            var newRowRequest: LaboratoryReportRequest = { ...newRow, date: new Date(newRow?.date?.toString()!) }
            await schema.validate(newRow, {abortEarly: false})
            if (newRow?.id !== undefined) {
                laboratoryReports[currentEditingIndex!] = await laboratoryReportClient.updateReport({ laboratoryReportRequest: newRowRequest });
            } else {
                laboratoryReports.push(await laboratoryReportClient.createReport({ laboratoryReportRequest: newRowRequest }));
            }
            cancel()
            setLaboratoryReports([...laboratoryReports]);
        } catch (err) {
            if (err instanceof Yup.ValidationError) {
                const validationErrors: { [key: string]: string } = {};
                err.inner.forEach((error) => {
                    if (error.path) {
                        validationErrors[error.path] = error.message;
                    }
                });
                setErrors(validationErrors);
            }
        }
    };

    async function getParams() {
        if (user === undefined) {
            return;
        }
        if (user?.userType !== "USER") {
            setLaboratoryReports(await laboratoryReportClient.getAllReports());
            setUserDropdown(await userClient.getUsersAsDropdown());
        } else {
            setLaboratoryReports(await laboratoryReportClient.getMyReports());
        }
    }

    async function search(filter: number | undefined) {
        if (filter === undefined) {
            setLaboratoryReports(await laboratoryReportClient.getAllReports());
            return;
        }
        setLaboratoryReports(await laboratoryReportClient.getAllReportsForUser({ userId: filter }));
    }

    function onRowClick(index: number) {
        navigate("/laboratoryValues/" + laboratoryReports[index].id);
    }

    useEffect(() => {
        getParams();
    }, []);

    return <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
        {user?.userType !== "USER" ? <h1>All patients and their reports</h1> : <h1>My reports</h1>}

        {user?.userType !== "USER" ? <FormControl fullWidth>
            <InputLabel id="user_id_label">User id</InputLabel>
            <Select
                labelId="user_id_label"
                id="user_id_select"
                label="User id"
                onChange={(event) => search(event.target.value as number | undefined)}
            >
                <MenuItem value={undefined}>empty</MenuItem>
                {userDropdown.map((user) => {
                    return <MenuItem value={user.id}>{user.email}</MenuItem>
                })}
            </Select>
        </FormControl> : <></>}
        <LaboratoryReportTableComponent
            laboratoryReports={laboratoryReports}
            currentEditingIndex={currentEditingIndex}
            newLaboratoryReport={newRow}
            userDropdown={userDropdown}
            onChange={setNewRow}
            submit={submitNewRow}
            delete={deleteRow}
            onUpdate={onUpdate}
            cancel={cancel}
            onRowClick={onRowClick}
            validationErrors={errors}
        />
        {user?.userType !== "USER" ? <Button variant="contained" onClick={() => {
            if (isNewRow) {
                return;
            }
            setIsNewRow(true);
            setNewRow({});
        }} sx={{ marginTop: "10px" }}>Add new row</Button> : <></>}
    </Box>
}

export default LaboratoryReportTableState;