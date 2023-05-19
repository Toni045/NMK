import { Box } from "@mui/material";
import Header from "../containers/Header/Header";
import { withAuthenticationRequired } from "@auth0/auth0-react";
import UsersTable from "../containers/UsersTable/UsersTable";
import { useContext } from "react";
import { UserContext } from "../store/UserContext";

function Component() {
    const { user } = useContext(UserContext);
    return <>
        {user?.userType === "ADMIN" ? <div style={{ width: "100vw", height: "100vh" }}>
            <Header />
            <h1>All users</h1>
            <Box sx={{ display: "flex", alignItems: "center", flexDirection: "column" }}>
                <UsersTable />
            </Box>
        </div> : <h1>You must be admin to view</h1>}
    </>
}
const Users = withAuthenticationRequired(Component, {
    // Show a message while the user waits to be redirected to the login page.
    onRedirecting: () => <div>Redirecting you to the login page...</div>,
});

export default Users;