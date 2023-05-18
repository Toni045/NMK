import { useMemo } from "react";
import LaboratoryValuesTableState from "./LaboratoryValuesTableState";

interface LaboratoryValuesTableProps {
    laboratoryReportId: number
}

function LaboratoryValuesTable(props: LaboratoryValuesTableProps) {
    const laboratoryReportId = useMemo(() => props.laboratoryReportId, [props.laboratoryReportId]);

    return <LaboratoryValuesTableState laboratoryReportId={laboratoryReportId} />
}

export default LaboratoryValuesTable;