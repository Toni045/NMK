import React, { PropsWithChildren, useCallback, useMemo } from "react";
import { Configuration, LaboratoryReportControllerApi, LaboratoryValueNameControllerApi, LaboratoryValuesControllerApi, UserControllerApi } from "../api";
import { useAuth0 } from "@auth0/auth0-react";
import configData from "../config.json";

interface ClientsContextState {
    laboratoryReportClient: LaboratoryReportControllerApi,
    userClient: UserControllerApi,
    laboratoryValueClient: LaboratoryValuesControllerApi,
    laboratoryValueNameClient: LaboratoryValueNameControllerApi
}

const defaultLaboratoryReportClient = new LaboratoryReportControllerApi();
const defaultUserClient = new UserControllerApi();
const defaultLaboratoryValueClient = new LaboratoryValuesControllerApi();
const defaultLaboratoryValueNameClient = new LaboratoryValueNameControllerApi();


export const ClientsContext = React.createContext<ClientsContextState>({
    laboratoryReportClient: defaultLaboratoryReportClient,
    userClient: defaultUserClient,
    laboratoryValueClient: defaultLaboratoryValueClient,
    laboratoryValueNameClient: defaultLaboratoryValueNameClient
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
            laboratoryReportClient: new LaboratoryReportControllerApi(new Configuration({ accessToken: getAccessToken })),
            userClient: new UserControllerApi(new Configuration({ accessToken: getAccessToken })),
            laboratoryValueClient: new LaboratoryValuesControllerApi(new Configuration({ accessToken: getAccessToken })),
            laboratoryValueNameClient: new LaboratoryValueNameControllerApi(new Configuration({ accessToken: getAccessToken }))
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