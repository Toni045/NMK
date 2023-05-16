import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import { LaboratoryReportDTO } from "../../api";
import { useCallback } from "react";
interface LaboratoryReportTableComponentProps {
    laboratoryReports: Array<LaboratoryReportDTO>
}

function LaboratoryReportTableComponent(props: LaboratoryReportTableComponentProps) {
    const getRows = useCallback(
        () => {
            return props.laboratoryReports?.map((row) => (
                <TableRow
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
                </TableRow>
            ))
        },
        [props.laboratoryReports]);

    return (<TableContainer component={Paper} sx={{ width: "65%", marginTop: "10px" }}>
        <Table aria-label="simple table">
            <TableHead>
                <TableRow>
                    <TableCell>Id</TableCell>
                    <TableCell>Date</TableCell>
                    <TableCell>Description</TableCell>
                    <TableCell>email</TableCell>
                    <TableCell>name</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {getRows()}
            </TableBody>
        </Table>
    </TableContainer>);
}

export default LaboratoryReportTableComponent;