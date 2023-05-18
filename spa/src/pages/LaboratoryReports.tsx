import { Box } from "@mui/material";
import LaboratoryReportTable from "../containers/LaboratoryReportTable/LaboratoryReportTable";
import Header from "../containers/Header/Header";
import { withAuthenticationRequired } from "@auth0/auth0-react";

function Component() {
    return <div style={{ width: "100vw", height: "100vh" }}>
        <Header />
        <Box sx={{ display: "flex", alignItems: "center", flexDirection: "column" }}>
            <LaboratoryReportTable />
        </Box>
    </div>
}
const LaboratoryReports = withAuthenticationRequired(Component, {
    // Show a message while the user waits to be redirected to the login page.
    onRedirecting: () => <div>Redirecting you to the login page...</div>,
});

export default LaboratoryReports;