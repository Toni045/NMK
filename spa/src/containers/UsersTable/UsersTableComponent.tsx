import { Box, Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField } from "@mui/material";
import { UserDTO } from "../../api";
import { useCallback, useContext } from "react";
import Colors from "../../colors.json";
import { UserContext } from "../../store/UserContext";

interface UsersTableComponentProps {
    users: Array<UserDTO>,
    newUserType: number | undefined,
    currentEditingIndex: number | undefined,
    onChange: (request: number | undefined) => void,
    submit: () => void,
    onUpdate: (id: number) => void,
    cancel: () => void
}

function UsersTableComponent(props: UsersTableComponentProps) {
    const { user } = useContext(UserContext);

    const getRows = useCallback(
        () => {
            return props.users?.map((row, index) => {
                return <TableRow
                    key={row.id}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                    <TableCell component="th" scope="row">
                        {row.id}
                    </TableCell>
                    <TableCell>
                        {row.name}
                    </TableCell>
                    <TableCell>
                        {row.email}
                    </TableCell>
                    <TableCell>
                        {props.currentEditingIndex !== index ?
                            row.userType
                            : <TextField
                                id="userType"
                                label="User type"
                                variant="outlined"
                                type="number"
                                onChange={(event) => props.onChange(event.target.value !== undefined ? Number.parseInt(event.target.value) : undefined)}
                                sx={{ marginRight: "5px" }} />}
                    </TableCell>
                    <TableCell>
                        {props.currentEditingIndex === index ?
                            <Box sx={{ display: "flex", flexDirection: "row" }}>
                                <Button variant="contained" onClick={() => {
                                    props.submit();
                                }} >Submit</Button>
                                <Button variant="contained" onClick={() => {
                                    props.cancel();
                                }} sx={{ backgroundColor: "orange" }}>Cancel</Button>
                            </Box>
                            : <Button variant="contained" onClick={() => {
                                props.onUpdate(index);
                            }} sx={{ backgroundColor: Colors.FernGreen }}>Update</Button>}
                    </TableCell>
                </TableRow>
            })
        },
        [props]);



    return (<TableContainer component={Paper} sx={{ marginTop: "10px" }}>
        <Table aria-label="simple table">
            <TableHead>
                <TableRow>
                    <TableCell>Id</TableCell>
                    <TableCell>Name</TableCell>
                    <TableCell>Email</TableCell>
                    <TableCell>User type</TableCell>
                    <TableCell>Actions</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {getRows()}
            </TableBody>
        </Table>
    </TableContainer>
    );
}

export default UsersTableComponent;