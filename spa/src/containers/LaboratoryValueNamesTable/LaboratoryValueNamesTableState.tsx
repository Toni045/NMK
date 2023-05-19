import { useContext, useEffect, useState } from "react";
import { LaboratoryValueNameDropdownDTO } from "../../api";
import { ClientsContext } from "../../store/ClientsContext";
import { Box, Button } from "@mui/material";
import LaboratoryValueNamesTableComponent from "./LaboratoryValueNamesTableComponent";

function LaboratoryValueNamesTableState() {
    const [laboratoryValueNames, setLaboratoryValueNames] = useState<Array<LaboratoryValueNameDropdownDTO>>([]);
    const [newName, setNewName] = useState<string | undefined>();
    const [isNewRow, setIsNewRow] = useState<boolean>(false);
    const [currentEditingIndex, setCurrentEditingIndex] = useState<number | undefined>(undefined);
    const { laboratoryValueNameClient } = useContext(ClientsContext);

    function onUpdate(index: number) {
        setCurrentEditingIndex(index);
        setNewName(laboratoryValueNames[index].name);
    }

    function cancel() {
        setIsNewRow(false);
        setNewName(undefined);
        setCurrentEditingIndex(undefined);
    }

    async function deleteRow(id: number) {
        await laboratoryValueNameClient.deleteLaboratoryValueName({ id: id });
        var values: Array<LaboratoryValueNameDropdownDTO> = [];
        laboratoryValueNames.forEach(elem => {
            if (elem.id !== id)
                values.push(elem);
        });
        setLaboratoryValueNames([...values]);
    }

    async function submitNewRow() {
        if (!isNewRow) {
            laboratoryValueNames[currentEditingIndex!] = await laboratoryValueNameClient.updateLaboratoryValueName({ id: laboratoryValueNames[currentEditingIndex!].id!, body: newName! });
        } else {
            laboratoryValueNames.push(await laboratoryValueNameClient.createLaboratoryValueName({ body: newName! }));
        }
        cancel()
        setLaboratoryValueNames([...laboratoryValueNames]);
    }

    async function getParams() {
        setLaboratoryValueNames(await laboratoryValueNameClient.getLaboratoryValueNameDropdown());
    }

    useEffect(() => {
        getParams();
    }, []);

    return <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
        <LaboratoryValueNamesTableComponent
            cancel={cancel}
            currentEditingIndex={currentEditingIndex}
            delete={deleteRow}
            isNewRow={isNewRow}
            laboratoryValueNames={laboratoryValueNames}
            newName={newName}
            onChange={setNewName}
            onUpdate={onUpdate}
            submit={submitNewRow} />
        <Button variant="contained" onClick={() => {
            if (isNewRow) {
                return;
            }
            setIsNewRow(true);
            setNewName("");
        }} sx={{ marginTop: "10px" }}>Add new row</Button>
    </Box>
}

export default LaboratoryValueNamesTableState;