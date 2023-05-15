import { useMemo } from "react"

interface TestAnonymusComponentProps {
    message: string
}

function TestAnonymusComponent(props: TestAnonymusComponentProps) {
    const message: string = useMemo(() => props.message, [props.message]);
    return (<span>{message}</span>);
}

export default TestAnonymusComponent;