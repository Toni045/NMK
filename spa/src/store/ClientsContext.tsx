import React, { PropsWithChildren, useCallback, useMemo } from "react";
import { Configuration, TestControllerApi } from "../api";
import { useAuth0 } from "@auth0/auth0-react";
import configData from "../config.json";

interface ClientsContextState {
    testClient: TestControllerApi
}

const defaultTestClient = new TestControllerApi();

export const ClientsContext = React.createContext<ClientsContextState>({
    testClient: defaultTestClient
});

interface ClientsContextProviderProps {
}

function ClientsContextProvider(props: PropsWithChildren<ClientsContextProviderProps>) {
    const { getAccessTokenSilently, isAuthenticated } = useAuth0();

    const getAccessToken = useCallback(async (name?: string | undefined, scopes?: string[] | undefined) => {
        try {
            let token: string = await getAccessTokenSilently({
                authorizationParams: {
                    audience: configData.audience,
                    scope: configData.scope
                }
            });
            return token;
        } catch (error) {
            return "";
        }
    }, [getAccessTokenSilently, isAuthenticated]);

    const contextState: ClientsContextState = useMemo(() => {
        let clients: ClientsContextState = {
            testClient: new TestControllerApi(new Configuration({ accessToken: getAccessToken }))
        };

        return clients;
    }, [getAccessToken]);

    return (
        <ClientsContext.Provider value={contextState}>
            {props.children}
        </ClientsContext.Provider>
    );
}

export default ClientsContextProvider;