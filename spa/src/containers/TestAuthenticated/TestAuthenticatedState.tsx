import { useContext, useEffect, useState } from "react";
import TestAuthenticatedComponent from "./TestAuthenticatedComponent";
import { ClientsContext } from "../../store/ClientsContext";

function TestAuthenticatedState() {
    const [message, setMessage] = useState<string>("");
    const { testClient } = useContext(ClientsContext);

    async function getMessage() {
        setMessage(await testClient.getSecuredTestString());
    }

    useEffect(() => {
        getMessage();
    });

    return (<TestAuthenticatedComponent message={message} />);
}

export default TestAuthenticatedState;