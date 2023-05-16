import { Box } from "@mui/material";
import LaboratoryReportTable from "../containers/LaboratoryReportTable/LaboratoryReportTable";
import Header from "../containers/Header/Header";

function LaboratoryReports() {
    return <div style={{ width: "100vw", height: "100vh" }}>
        <Header />
        <Box sx={{ display: "flex", alignItems: "center", flexDirection: "column" }}>
            <h1>All patients and their reports</h1>
            <LaboratoryReportTable />
        </Box>
    </div>
}

export default LaboratoryReports;