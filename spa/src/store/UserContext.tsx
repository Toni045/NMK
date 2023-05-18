import React, { PropsWithChildren, useMemo, useState } from "react";
import { UserDTO } from "../api";


interface UserContextState {
    user: UserDTO | undefined,
    setUser: React.Dispatch<React.SetStateAction<UserDTO | undefined>>
}

export const UserContext = React.createContext<UserContextState>({
    user: undefined,
    setUser: () => { }
});

interface UserContextProviderProps {
}

function UserContextProvider(props: PropsWithChildren<UserContextProviderProps>) {
    const [user, setUser] = useState<UserDTO>();

    const contextState: UserContextState = useMemo(() => {
        return {
            user: user,
            setUser: setUser
        };
    }, [user, setUser]);


    return (
        <UserContext.Provider value={contextState}>
            {props.children}
        </UserContext.Provider>
    );
}

export default UserContextProvider;