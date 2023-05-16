import { useContext, useEffect, useState } from "react";
import { LaboratoryReportDTO, LaboratoryReportRequest } from "../../api";
import { ClientsContext } from "../../store/ClientsContext";
import LaboratoryReportTableComponent from "./LaboratoryReportTableComponent";
import { Box, Button } from "@mui/material";

function LaboratoryReportTableState() {
    const [laboratoryReports, setLaboratoryReports] = useState<Array<LaboratoryReportDTO>>([]);
    const [newRow, setNewRow] = useState<LaboratoryReportRequest | undefined>();
    const [isNewRow, setIsNewRow] = useState<boolean>(false);
    const [currentEditingIndex, setCurrentEditingIndex] = useState<number | undefined>(undefined);
    const { laboratoryReportClient } = useContext(ClientsContext);

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
    }

    async function deleteRow(index: number) {
        await laboratoryReportClient.deleteReport({ id: index });
        laboratoryReports.splice(index, 1);
        setLaboratoryReports([...laboratoryReports]);
    }

    async function submitNewRow() {
        var newRowRequest: LaboratoryReportRequest = { ...newRow, date: new Date(newRow?.date?.toString()!) }
        if (newRow?.id !== undefined) {
            laboratoryReports[currentEditingIndex!] = await laboratoryReportClient.updateReport({ laboratoryReportRequest: newRowRequest });
        } else {
            laboratoryReports.push(await laboratoryReportClient.createReport({ laboratoryReportRequest: newRowRequest }));
        }
        cancel()
        setLaboratoryReports([...laboratoryReports]);
    }

    async function getLaboratoryReports() {
        setLaboratoryReports(await laboratoryReportClient.getAllReports());
    }

    useEffect(() => {
        getLaboratoryReports();
    });

    return <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
        <LaboratoryReportTableComponent
            laboratoryReports={laboratoryReports}
            currentEditingIndex={currentEditingIndex}
            newLaboratoryReport={newRow}
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