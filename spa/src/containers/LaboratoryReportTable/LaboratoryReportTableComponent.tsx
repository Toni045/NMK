import {
    Box,
    Button,
    FormControl,
    FormHelperText,
    InputLabel,
    MenuItem,
    Paper,
    Select,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    TextField
} from "@mui/material";
import { LaboratoryReportDTO, LaboratoryReportRequest, UserDropdownDTO } from "../../api";
import { useCallback, useContext } from "react";
import Colors from "../../colors.json"
import { TableRowStyle } from "./LaboratoryReportTable.styles";
import { UserContext } from "../../store/UserContext";

interface LaboratoryReportTableComponentProps {
    laboratoryReports: Array<LaboratoryReportDTO>,
    newLaboratoryReport: LaboratoryReportRequest | undefined,
    currentEditingIndex: number | undefined,
    userDropdown: Array<UserDropdownDTO>,
    onChange: (request: LaboratoryReportRequest) => void,
    submit: () => void,
    delete: (id: number) => void,
    onUpdate: (index: number) => void,
    cancel: () => void,
    onRowClick: (index: number) => void
    validationErrors: { [key: string]: string };
}

function LaboratoryReportTableComponent(props: LaboratoryReportTableComponentProps) {
    const { user } = useContext(UserContext);

    const getUserDropdown = useCallback((onChange: (event: any) => void) => {
        return <FormControl fullWidth>
            <InputLabel id="user_id_label">User id</InputLabel>
            <Select
                labelId="user_id_label"
                id="user_id_select"
                value={props.newLaboratoryReport?.userId}
                label="User id"
                onChange={onChange}
            >
                <MenuItem value={undefined}>empty</MenuItem>
                {props.userDropdown.map((user) => {
                    return <MenuItem value={user.id}>{user.email}</MenuItem>
                })}
            </Select>
        </FormControl>
    }, [props.newLaboratoryReport?.userId, props.userDropdown]);
    const getNewRow = useCallback(
        (update: boolean) => {
            if (props.newLaboratoryReport === undefined) {
                return;
            }
            if (props.newLaboratoryReport.id !== undefined && !update) {
                return;
            }
            var starting: number = props.laboratoryReports.length;
            return (
                <TableRow
                    key={++starting}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                    <TableCell />
                    <TableCell>
                        <TextField
                            id="date"
                            variant="outlined"
                            type="date"
                            value={props.newLaboratoryReport.date?.toISOString().substring(0, 10)}
                            onChange={(event: any) => props.onChange({ ...props.newLaboratoryReport, date: new Date(event.target.value.toString()) })}
                            sx={{ marginRight: "5px" }} />
                        {props.validationErrors.date && (
                            <div style={{ color: "red" }}>{props.validationErrors.date}</div>
                        )}
                    </TableCell>
                    <TableCell>
                        <TextField
                            id="description"
                            label="Report description"
                            variant="outlined"
                            value={props.newLaboratoryReport.description}
                            onChange={(event: any) => props.onChange({ ...props.newLaboratoryReport, description: event.target.value })}
                            sx={{ marginRight: "5px" }} />
                        {props.validationErrors.description && (
                                <div style={{ color: "red" }}>{props.validationErrors.description}</div>
                        )}
                    </TableCell>

                    <TableCell>
                        {getUserDropdown((event: any) => {
                            const selectedValue = event.target.value;
                            props.onChange({ ...props.newLaboratoryReport, userId: selectedValue })
                            if (selectedValue === undefined) {
                                return <div style={{ color: "red" }}>{props.validationErrors.userId}</div>;
                            } else {
                                delete props.validationErrors.userId
                            }
                        })}
                        {props.validationErrors.userId && (
                            <div style={{ color: "red" }}>{props.validationErrors.userId}</div>
                        )}
                    </TableCell>
                    <TableCell />
                    <TableCell>
                        <Box sx={{ display: "flex", flexDirection: "row" }}>
                            <Button variant="contained" onClick={() => {
                                props.submit();
                            }} >Submit</Button>
                            <Button variant="contained" onClick={() => {
                                props.cancel();
                            }} sx={{ backgroundColor: "orange" }}>Cancel</Button>
                        </Box>
                    </TableCell>
                </TableRow>
            )
        },
        [props, getUserDropdown]);

    const getRows = useCallback(
        () => {
            return props.laboratoryReports?.map((row, index) => {
                if (props.currentEditingIndex === index) {
                    return getNewRow(true);
                }
                return <TableRow
                    key={row.id}
                    sx={{ ...TableRowStyle, '&:last-child td, &:last-child th': { border: 0 } }}
                >
                    <TableCell component="th" scope="row" onClick={() => props.onRowClick(index)}>
                        {row.id}
                    </TableCell>
                    <TableCell onClick={() => props.onRowClick(index)}>
                        {row.date?.toDateString()}
                    </TableCell>
                    <TableCell onClick={() => props.onRowClick(index)}>
                        {row.description}
                    </TableCell>
                    <TableCell onClick={() => props.onRowClick(index)}>
                        {row.userEmail}
                    </TableCell>
                    <TableCell onClick={() => props.onRowClick(index)}>
                        {row.userName}
                    </TableCell>
                    {user?.userType !== "USER" ?
                        <TableCell>
                            <Box sx={{ display: "flex", flexDirection: "row" }}>
                                <Button variant="contained" onClick={() => {
                                    props.delete(row.id!);
                                }} sx={{ backgroundColor: "red" }}>Delete</Button>
                                <Button variant="contained" onClick={() => {
                                    props.onUpdate(index);
                                }} sx={{ backgroundColor: Colors.FernGreen }}>Update</Button>
                            </Box>
                        </TableCell> : <></>}
                </TableRow>
            })
        },
        [props, getNewRow]);



    return (
        <TableContainer component={Paper} sx={{ width: "65%", marginTop: "10px" }}>
            <Table aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>Id</TableCell>
                        <TableCell>Date</TableCell>
                        <TableCell>Description</TableCell>
                        <TableCell>email</TableCell>
                        <TableCell>name</TableCell>
                        {user?.userType !== "USER" ? <TableCell>actions</TableCell> : <></>}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {getRows()}
                    {user?.userType !== "USER" ? getNewRow(false) : <></>}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default LaboratoryReportTableComponent;