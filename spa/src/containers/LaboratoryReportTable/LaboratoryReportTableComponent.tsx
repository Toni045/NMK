import { Box, Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField } from "@mui/material";
import { LaboratoryReportDTO, LaboratoryReportRequest } from "../../api";
import { useCallback } from "react";
import Colors from "../../colors.json"

interface LaboratoryReportTableComponentProps {
    laboratoryReports: Array<LaboratoryReportDTO>,
    newLaboratoryReport: LaboratoryReportRequest | undefined,
    currentEditingIndex: number | undefined,
    onChange: (request: LaboratoryReportRequest) => void,
    submit: () => void,
    delete: (id: number) => void,
    onUpdate: (index: number) => void,
    cancel: () => void
}

function LaboratoryReportTableComponent(props: LaboratoryReportTableComponentProps) {
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
                    </TableCell>
                    <TableCell>
                        <TextField
                            id="description"
                            label="Report description"
                            variant="outlined"
                            value={props.newLaboratoryReport.description}
                            onChange={(event: any) => props.onChange({ ...props.newLaboratoryReport, description: event.target.value })}
                            sx={{ marginRight: "5px" }} />
                    </TableCell>
                    <TableCell>
                        <TextField
                            id="user_id"
                            label="User id"
                            variant="outlined"
                            type="number"
                            value={props.newLaboratoryReport.userId}
                            onChange={(event: any) => props.onChange({ ...props.newLaboratoryReport, userId: event.target.value })}
                            sx={{ marginRight: "5px" }} />
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
        [props]);

    const getRows = useCallback(
        () => {
            return props.laboratoryReports?.map((row, index) => {
                if (props.currentEditingIndex === index) {
                    return getNewRow(true);
                }
                return <TableRow
                    key={row.id}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                    <TableCell component="th" scope="row">
                        {row.id}
                    </TableCell>
                    <TableCell>
                        {row.date?.toDateString()}
                    </TableCell>
                    <TableCell>
                        {row.description}
                    </TableCell>
                    <TableCell>
                        {row.userEmail}
                    </TableCell>
                    <TableCell>
                        {row.userName}
                    </TableCell>
                    <TableCell>
                        <Box sx={{ display: "flex", flexDirection: "row" }}>
                            <Button variant="contained" onClick={() => {
                                props.delete(row.id!);
                            }} sx={{ backgroundColor: "red" }}>Delete</Button>
                            <Button variant="contained" onClick={() => {
                                props.onUpdate(index);
                            }} sx={{ backgroundColor: Colors.FernGreen }}>Update</Button>
                        </Box>
                    </TableCell>
                </TableRow>
            })
        },
        [props, getNewRow]);



    return (<TableContainer component={Paper} sx={{ width: "65%", marginTop: "10px" }}>
        <Table aria-label="simple table">
            <TableHead>
                <TableRow>
                    <TableCell>Id</TableCell>
                    <TableCell>Date</TableCell>
                    <TableCell>Description</TableCell>
                    <TableCell>email</TableCell>
                    <TableCell>name</TableCell>
                    <TableCell>actions</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {getRows()}
                {getNewRow(false)}
            </TableBody>
        </Table>
    </TableContainer>);
}

export default LaboratoryReportTableComponent;