import { Box } from "@mui/material";
import Header from "../containers/Header/Header";
import { withAuthenticationRequired } from "@auth0/auth0-react";
import { useContext } from "react";
import { UserContext } from "../store/UserContext";
import LaboratoryValueNamesTable from "../containers/LaboratoryValueNamesTable/LaboratoryValueNamesTable";

function Component() {
    const { user } = useContext(UserContext);
    return <>
        {user?.userType !== "USER" ? <div style={{ width: "100vw", height: "100vh" }}>
            <Header />
            <h1>All laboratory value names</h1>
            <Box sx={{ display: "flex", alignItems: "center", flexDirection: "column" }}>
                <LaboratoryValueNamesTable />
            </Box>
        </div> : <h1>You must be admin or doctor to view</h1>}
    </>
}
const LaboratoryValueNames = withAuthenticationRequired(Component, {
    // Show a message while the user waits to be redirected to the login page.
    onRedirecting: () => <div>Redirecting you to the login page...</div>,
});

export default LaboratoryValueNames;