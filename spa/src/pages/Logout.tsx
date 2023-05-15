import { useAuth0 } from "@auth0/auth0-react";
import { useEffect } from "react";

function Logout() {
    const {
        isLoading,
        error,
        logout,
    } = useAuth0();

    useEffect(() => {
        logout({
            logoutParams: {
                returnTo: window.location.origin
            }
        });
    })

    if (error) {
        return <div>Oops... {error.message}</div>;
    }

    if (isLoading) {
        return <div>Loading...</div>;
    }

    return <></>
}

export default Logout;