import { Box } from "@mui/material";
import Header from "../containers/Header/Header";
import { useParams } from "react-router-dom";
import LaboratoryValuesTable from "../containers/LaboratoryValuesTable/LaboratoryValuesTable";

function LaboratoryValues() {
    const { laboratoryReportId } = useParams();
    return <div style={{ width: "100vw", height: "100vh" }}>
        <Header />
        <Box sx={{ display: "flex", alignItems: "center", flexDirection: "column" }}>
            <h1>All values for report with id: {laboratoryReportId}</h1>
            <LaboratoryValuesTable laboratoryReportId={Number.parseInt(laboratoryReportId!)} />
        </Box>
    </div>
}

export default LaboratoryValues;