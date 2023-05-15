import { withAuthenticationRequired } from "@auth0/auth0-react";
import TestAuthenticated from "../containers/TestAuthenticated/TestAuthenticated";


function Component() {
    return <TestAuthenticated />
}

const Authenticated = withAuthenticationRequired(Component, {
    // Show a message while the user waits to be redirected to the login page.
    onRedirecting: () => <div>Redirecting you to the login page...</div>,
});


export default Authenticated;