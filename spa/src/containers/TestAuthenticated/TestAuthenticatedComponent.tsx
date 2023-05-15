import { useMemo } from "react";

interface TestAuthenticatedComponentProps {
    message: string
}

function TestAuthenticatedComponent(props: TestAuthenticatedComponentProps) {
    const message: string = useMemo(() => props.message, [props.message]);

    return (<span>{message}</span>);
}

export default TestAuthenticatedComponent;