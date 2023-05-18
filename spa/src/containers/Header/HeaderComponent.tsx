import { Box, Link, Typography } from "@mui/material";
import React, { useCallback } from "react";
import { useMemo } from "react";
import { useNavigate } from "react-router-dom";
import Colors from "../../colors.json";
import { Header, LinkStyle } from "./Header.styles";

interface HeaderComponentProps {
    isLoggedIn: boolean;
    activeIndex: number
}

function HeaderComponent(props: HeaderComponentProps) {
    const isLoggedIn = useMemo(() => props.isLoggedIn, [props.isLoggedIn]);
    const activeIndex = useMemo(() => props.activeIndex, [props.activeIndex]);
    const navigate = useNavigate();

    const getLoginOrLogout = useCallback((): JSX.Element => {
        if (isLoggedIn) {
            return (<Typography sx={{ minWidth: 100 }}>
                <Link onClick={() => navigate("/logout")} sx={{ ...LinkStyle }}>
                    Logout
                </Link>
            </Typography>);
        }
        return (<Typography sx={{ minWidth: 100 }}>
            <Link onClick={() => navigate("/login")} sx={{ ...LinkStyle }}>
                Login
            </Link>
        </Typography>);
    }, [isLoggedIn, navigate]);

    return (<React.Fragment>
        <Box sx={{ ...Header }}>
            <Box sx={{ display: 'flex', alignItems: 'end', textAlign: 'center', justifyContent: 'space-between', padding: "10px" }}>
                <Box sx={{ display: 'flex', alignItems: 'center', textAlign: 'center' }}>
                    <Typography sx={{ minWidth: 100 }}>
                        <Link onClick={() => navigate("/")} sx={{ ...LinkStyle, color: activeIndex === 0 ? Colors.DarkMossGreen : "black" }}>
                            Home
                        </Link>
                    </Typography>
                    <Typography sx={{ minWidth: 100 }}>
                        <Link onClick={() => navigate("/laboratoryReports")} sx={{ ...LinkStyle, color: activeIndex === 1 ? Colors.DarkMossGreen : "black" }}>
                            Laboratory reports
                        </Link>
                    </Typography>
                    <Typography sx={{ marginLeft: "10px", minWidth: 100 }}>
                        <Link onClick={() => navigate("/laboratoryReports")} sx={{ ...LinkStyle, color: activeIndex === 2 ? Colors.DarkMossGreen : "black" }}>
                            Laboratory values
                        </Link>
                    </Typography>
                </Box>
                <Typography sx={{ minWidth: 100 }}>
                    {
                        getLoginOrLogout()
                    }
                </Typography>
            </Box>
        </Box>
    </React.Fragment >);
}

export default HeaderComponent;