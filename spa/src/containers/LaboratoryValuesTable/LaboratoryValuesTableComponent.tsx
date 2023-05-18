import { Box, Button, FormControl, InputLabel, MenuItem, Paper, Select, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Typography } from "@mui/material";
import { LaboratoryReportDTO, LaboratoryValueDTO, LaboratoryValueNameDropdownDTO, LaboratoryValueRequest, UserDropdownDTO } from "../../api";
import { useCallback, useMemo } from "react";
import Colors from "../../colors.json";

interface LaboratoryValuesTableComponentProps {
    laboratoryValues: Array<LaboratoryValueDTO>,
    newLaboratoryValue: LaboratoryValueRequest | undefined,
    currentEditingIndex: number | undefined,
    laboratoryValueNameDropdown: Array<LaboratoryValueNameDropdownDTO>,
    laboratoryReport: LaboratoryReportDTO | undefined,
    onChange: (request: LaboratoryValueRequest) => void,
    submit: () => void,
    delete: (id: number) => void,
    onUpdate: (id: number) => void,
    cancel: () => void
}

function LaboratoryValuesTableComponent(props: LaboratoryValuesTableComponentProps) {
    const laboratoryReport = useMemo(() => props.laboratoryReport, [props.laboratoryReport]);

    const getUserDropdown = useCallback((onChange: (event: any) => void) => {
        return <FormControl fullWidth>
            <InputLabel id="laboratory_value_label">Laboratory value</InputLabel>
            <Select
                labelId="laboratory_value_label"
                id="laboratory_value_select"
                value={props.newLaboratoryValue?.laboratoryValueNameId}
                label="laboratory value"
                onChange={onChange}
            >
                <MenuItem value={undefined}>empty</MenuItem>
                {props.laboratoryValueNameDropdown.map((value) => {
                    return <MenuItem value={value.id}>{value.name}</MenuItem>
                })}
            </Select>
        </FormControl>
    }, [props.newLaboratoryValue?.laboratoryValueNameId, props.laboratoryValueNameDropdown]);

    const getNewRow = useCallback(
        (update: boolean) => {
            if (props.newLaboratoryValue === undefined) {
                return;
            }
            if (props.currentEditingIndex !== undefined && !update) {
                return;
            }
            var starting: number = props.laboratoryValues.length;
            return (
                <TableRow
                    key={++starting}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                    <TableCell />
                    <TableCell>
                        {getUserDropdown((event: any) => props.onChange({ ...props.newLaboratoryValue, laboratoryValueNameId: event.target.value }))}
                    </TableCell>
                    <TableCell>
                        <TextField
                            id="value"
                            label="Value"
                            variant="outlined"
                            type="number"
                            value={props.newLaboratoryValue.value}
                            onChange={(event: any) => props.onChange({ ...props.newLaboratoryValue, value: event.target.value })}
                            sx={{ marginRight: "5px" }} />
                    </TableCell>
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
            return props.laboratoryValues?.map((row, index) => {
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
                        {row.laboratoryValueName}
                    </TableCell>
                    <TableCell>
                        {row.value}
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



    return (<Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
        <Box sx={{ display: "flex", flexDirection: "row", alignItems: "center" }}>
            <Typography sx={{ marginRight: "10px" }}>
                {laboratoryReport?.date?.toDateString()}
            </Typography>
            <Typography sx={{ marginRight: "10px" }}>
                {laboratoryReport?.description}
            </Typography>
            <Typography>
                {laboratoryReport?.userEmail}
            </Typography>
        </Box>
        <TableContainer component={Paper} sx={{ marginTop: "10px" }}>
            <Table aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>Id</TableCell>
                        <TableCell>Laboratory value</TableCell>
                        <TableCell>Value</TableCell>
                        <TableCell>Actions</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {getRows()}
                    {getNewRow(false)}
                </TableBody>
            </Table>
        </TableContainer>
    </Box >

    );
}

export default LaboratoryValuesTableComponent;