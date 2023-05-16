import { useContext, useEffect, useState } from "react";
import { LaboratoryReportDTO } from "../../api";
import { ClientsContext } from "../../store/ClientsContext";
import LaboratoryReportTableComponent from "./LaboratoryReportTableComponent";

function LaboratoryReportTableState() {
    const [laboratoryReports, setLaboratoryReports] = useState<Array<LaboratoryReportDTO>>([]);
    const { laboratoryReportClient } = useContext(ClientsContext);

    async function getLaboratoryReports() {
        setLaboratoryReports(await laboratoryReportClient.getAllReports());
    }

    useEffect(() => {
        getLaboratoryReports();
    });

    return <LaboratoryReportTableComponent laboratoryReports={laboratoryReports} />
}

export default LaboratoryReportTableState;