import React, { PropsWithChildren, useCallback, useMemo } from "react";
import { Configuration, LaboratoryReportControllerApi, TestControllerApi, UserControllerApi } from "../api";
import { useAuth0 } from "@auth0/auth0-react";
import configData from "../config.json";

interface ClientsContextState {
    testClient: TestControllerApi,
    laboratoryReportClient: LaboratoryReportControllerApi,
    userClient: UserControllerApi
}

const defaultTestClient = new TestControllerApi();
const defaultLaboratoryReportClient = new LaboratoryReportControllerApi();
const defaultUserClient = new UserControllerApi();

export const ClientsContext = React.createContext<ClientsContextState>({
    testClient: defaultTestClient,
    laboratoryReportClient: defaultLaboratoryReportClient,
    userClient: defaultUserClient
});

interface ClientsContextProviderProps {
}

function ClientsContextProvider(props: PropsWithChildren<ClientsContextProviderProps>) {
    const { getAccessTokenSilently } = useAuth0();

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
    }, [getAccessTokenSilently]);

    const contextState: ClientsContextState = useMemo(() => {
        let clients: ClientsContextState = {
            testClient: new TestControllerApi(new Configuration({ accessToken: getAccessToken })),
            laboratoryReportClient: new LaboratoryReportControllerApi(new Configuration({ accessToken: getAccessToken })),
            userClient: new UserControllerApi(new Configuration({ accessToken: getAccessToken }))
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