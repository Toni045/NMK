import { useContext, useEffect, useState } from "react";
import { ClientsContext } from "../../store/ClientsContext";
import TestAnonymusComponent from "./TestAnonymusComponent";

function TestAnonymusState() {
    const [message, setMessage] = useState<string>("");
    const { testClient } = useContext(ClientsContext);

    async function getMesssage() {
        setMessage(await testClient.getTestString());
    }

    useEffect(() => {
        getMesssage();
    });

    return (
        <TestAnonymusComponent message={message} />
    );
}

export default TestAnonymusState;