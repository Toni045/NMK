import { FallbackProps } from "react-error-boundary";

function FallbackComponent(props: FallbackProps) {
    return (
        <div role="alert">
            <p>Something went wrong:</p>
            <pre>{props.error.message}</pre>
            <button onClick={props.resetErrorBoundary}>Try again</button>
        </div>
    )
}

export default FallbackComponent;