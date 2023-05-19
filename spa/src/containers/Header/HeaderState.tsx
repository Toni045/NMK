import { useEffect, useState } from "react";
import HeaderComponent from "./HeaderComponent";
import { useAuth0 } from "@auth0/auth0-react";
import { useLocation } from "react-router-dom";

function HeaderState() {
    const [activeIndex, setActiveIndex] = useState<number>(0);
    const { isAuthenticated } = useAuth0();
    const location = useLocation();

    useEffect(() => {
        if (location.pathname === "/" || location.pathname === "") {
            setActiveIndex(0);
        } else if (location.pathname.toLowerCase().includes("laboratoryreports")) {
            setActiveIndex(1);
        } else if (location.pathname.toLowerCase().includes("laboratoryvalues")) {
            setActiveIndex(2);
        } else if (location.pathname.toLowerCase().includes("users")) {
            setActiveIndex(3);
        } else if (location.pathname.toLowerCase().includes("laboratoryvaluenames")) {
            setActiveIndex(4);
        }
    }, [location]);
    return (<HeaderComponent activeIndex={activeIndex} isLoggedIn={isAuthenticated} />)
}

export default HeaderState;