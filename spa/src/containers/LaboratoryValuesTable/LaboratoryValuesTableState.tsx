import { useContext, useEffect, useMemo, useState } from "react";
import { LaboratoryReportDTO, LaboratoryValueDTO, LaboratoryValueNameDropdownDTO, LaboratoryValueRequest } from "../../api";
import { ClientsContext } from "../../store/ClientsContext";
import LaboratoryReportTableComponent from "./LaboratoryValuesTableComponent";
import { Box, Button, FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import { useParams } from "react-router-dom";

interface LaboratoryValuesTableStateProps {
    laboratoryReportId: number
}

function LaboratoryReportTableState(props: LaboratoryValuesTableStateProps) {
    const [laboratoryValues, setLaboratoryValues] = useState<Array<LaboratoryValueDTO>>([]);
    const [laboratoryValueNameDropdown, setLaboratoryValueNameDropdown] = useState<Array<LaboratoryValueNameDropdownDTO>>([]);
    const [newRow, setNewRow] = useState<LaboratoryValueRequest | undefined>();
    const [isNewRow, setIsNewRow] = useState<boolean>(false);
    const [currentEditingIndex, setCurrentEditingIndex] = useState<number | undefined>(undefined);
    const [laboratoryReport, setLaboratoryReport] = useState<LaboratoryReportDTO | undefined>(undefined);
    const { laboratoryValueClient, laboratoryValueNameClient, laboratoryReportClient } = useContext(ClientsContext);
    const laboratoryReportId = useMemo(() => props.laboratoryReportId, [props.laboratoryReportId]);

    function onUpdate(index: number) {
        setCurrentEditingIndex(index);
        setNewRow({
            value: laboratoryValues[index].value,
            laboratoryValueNameId: laboratoryValues[index].laboratoryValueNameId,
        });
    }

    function cancel() {
        setIsNewRow(false);
        setNewRow(undefined);
        setCurrentEditingIndex(undefined);
    }

    async function deleteRow(id: number) {
        await laboratoryValueClient.deleteLaboratoryValue({ id: id });
        var values: Array<LaboratoryReportDTO> = [];
        laboratoryValues.forEach(elem => {
            if (elem.id !== id)
                values.push(elem);
        });
        setLaboratoryValues([...values]);
    }

    async function submitNewRow() {
        if (currentEditingIndex !== undefined) {
            laboratoryValues[currentEditingIndex!] = await laboratoryValueClient.updateLaboratoryValue({ id: laboratoryValues[currentEditingIndex!].id!, laboratoryValueRequest: newRow! });
        } else {
            laboratoryValues.push(await laboratoryValueClient.createNewLaboratoryValue({ laboratoryValueRequest: newRow!, laboratoryReportId: laboratoryReportId }));
        }
        cancel()
        setLaboratoryValues([...laboratoryValues]);
    }

    async function getParams() {
        setLaboratoryValues(await laboratoryValueClient.getAllLaboratoryValuesForLaboratoryReport({ laboratoryReportId: laboratoryReportId }));
        setLaboratoryValueNameDropdown(await laboratoryValueNameClient.getLaboratoryValueNameDropdown());
        setLaboratoryReport(await laboratoryReportClient.getReportById({ id: laboratoryReportId }));
    }

    async function search(filter: number | undefined) {
        if (filter === undefined) {
            setLaboratoryValues(await laboratoryValueClient.getAllLaboratoryValuesForLaboratoryReport({ laboratoryReportId: laboratoryReportId }));
            return;
        }
        setLaboratoryValues(await laboratoryValueClient.getAllLaboratoryValuesForLaboratoryReportWithName({ laboratoryReportId: laboratoryReportId, laboratoryValueNameId: filter }));
    }

    useEffect(() => {
        getParams();
    }, []);

    return <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
        <FormControl fullWidth>
            <InputLabel id="laboratory_value_label">Laboratory value</InputLabel>
            <Select
                labelId="laboratory_value_label"
                id="laboratory_value_select"
                label="User id"
                onChange={(event) => search(event.target.value as number | undefined)}
            >
                <MenuItem value={undefined}>empty</MenuItem>
                {laboratoryValueNameDropdown.map((value) => {
                    return <MenuItem value={value.id}>{value.name}</MenuItem>
                })}
            </Select>
        </FormControl>
        <LaboratoryReportTableComponent
            laboratoryValues={laboratoryValues}
            currentEditingIndex={currentEditingIndex}
            newLaboratoryValue={newRow}
            laboratoryValueNameDropdown={laboratoryValueNameDropdown}
            laboratoryReport={laboratoryReport}
            onChange={setNewRow}
            submit={submitNewRow}
            delete={deleteRow}
            onUpdate={onUpdate}
            cancel={cancel} />
        <Button variant="contained" onClick={() => {
            if (isNewRow) {
                return;
            }
            setIsNewRow(true);
            setNewRow({});
        }} sx={{ marginTop: "10px" }}>Add new row</Button>
    </Box>
}

export default LaboratoryReportTableState;