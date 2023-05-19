import { Box, Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField } from "@mui/material";
import { LaboratoryValueNameDropdownDTO } from "../../api";
import { useCallback } from "react";
import Colors from "../../colors.json";

interface LaboratoryValueNamesTableComponentProps {
    laboratoryValueNames: Array<LaboratoryValueNameDropdownDTO>,
    newName: string | undefined,
    currentEditingIndex: number | undefined,
    isNewRow: boolean,
    onChange: (request: string | undefined) => void,
    submit: () => void,
    delete: (id: number) => void,
    onUpdate: (index: number) => void,
    cancel: () => void
}

function LaboratoryValueNamesTableComponent(props: LaboratoryValueNamesTableComponentProps) {
    const getNewRow = useCallback(() => {
        if (!props.isNewRow) {
            return;
        }
        return <TableRow
            key={"newRow"}
        >
            <TableCell />
            <TableCell>
                <TextField
                    id="name"
                    label="Value name"
                    variant="outlined"
                    onChange={(event: any) => props.onChange(event.target.value)}
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
    }, [props.onChange, props.submit, props.cancel, props.isNewRow]);

    const getRows = useCallback(
        () => {
            return props.laboratoryValueNames?.map((row, index) => {
                return <TableRow
                    key={row.id}
                >
                    <TableCell component="th" scope="row" >
                        {row.id}
                    </TableCell>
                    <TableCell>
                        {props.currentEditingIndex !== index ?
                            row.name : <TextField
                                id="name"
                                label="Value name"
                                variant="outlined"
                                value={props.newName}
                                onChange={(event: any) => props.onChange(event.target.value)}
                                sx={{ marginRight: "5px" }} />}
                    </TableCell>
                    <TableCell>
                        {props.currentEditingIndex === index ? <Box sx={{ display: "flex", flexDirection: "row" }}>
                            <Button variant="contained" onClick={() => {
                                props.submit();
                            }} >Submit</Button>
                            <Button variant="contained" onClick={() => {
                                props.cancel();
                            }} sx={{ backgroundColor: "orange" }}>Cancel</Button>
                        </Box> :

                            <Box sx={{ display: "flex", flexDirection: "row" }}>
                                <Button variant="contained" onClick={() => {
                                    props.delete(row.id!);
                                }} sx={{ backgroundColor: "red" }}>Delete</Button>
                                <Button variant="contained" onClick={() => {
                                    props.onUpdate(index);
                                }} sx={{ backgroundColor: Colors.FernGreen }}>Update</Button>
                            </Box>
                        }
                    </TableCell>
                </TableRow>
            })
        },
        [props]);



    return (
        <TableContainer component={Paper} sx={{ marginTop: "10px" }}>
            <Table aria-label="simple table">
                <TableHead>
                    <TableRow >
                        <TableCell>Id</TableCell>
                        <TableCell>Value name</TableCell>
                        <TableCell>actions</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody >
                    {getRows()}
                    {getNewRow()}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default LaboratoryValueNamesTableComponent;