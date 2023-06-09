/* tslint:disable */
/* eslint-disable */
/**
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import * as runtime from '../runtime';
import type {
  LaboratoryValueDTO,
  LaboratoryValueRequest,
} from '../models';
import {
    LaboratoryValueDTOFromJSON,
    LaboratoryValueDTOToJSON,
    LaboratoryValueRequestFromJSON,
    LaboratoryValueRequestToJSON,
} from '../models';

export interface CreateNewLaboratoryValueRequest {
    laboratoryReportId: number;
    laboratoryValueRequest: LaboratoryValueRequest;
}

export interface DeleteLaboratoryValueRequest {
    id: number;
}

export interface GetAllLaboratoryValuesForLaboratoryReportRequest {
    laboratoryReportId: number;
}

export interface GetAllLaboratoryValuesForLaboratoryReportWithNameRequest {
    laboratoryReportId: number;
    laboratoryValueNameId: number;
}

export interface UpdateLaboratoryValueRequest {
    id: number;
    laboratoryValueRequest: LaboratoryValueRequest;
}

/**
 * 
 */
export class LaboratoryValuesControllerApi extends runtime.BaseAPI {

    /**
     */
    async createNewLaboratoryValueRaw(requestParameters: CreateNewLaboratoryValueRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<LaboratoryValueDTO>> {
        if (requestParameters.laboratoryReportId === null || requestParameters.laboratoryReportId === undefined) {
            throw new runtime.RequiredError('laboratoryReportId','Required parameter requestParameters.laboratoryReportId was null or undefined when calling createNewLaboratoryValue.');
        }

        if (requestParameters.laboratoryValueRequest === null || requestParameters.laboratoryValueRequest === undefined) {
            throw new runtime.RequiredError('laboratoryValueRequest','Required parameter requestParameters.laboratoryValueRequest was null or undefined when calling createNewLaboratoryValue.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/LaboratoryValues/{laboratoryReportId}`.replace(`{${"laboratoryReportId"}}`, encodeURIComponent(String(requestParameters.laboratoryReportId))),
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: LaboratoryValueRequestToJSON(requestParameters.laboratoryValueRequest),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => LaboratoryValueDTOFromJSON(jsonValue));
    }

    /**
     */
    async createNewLaboratoryValue(requestParameters: CreateNewLaboratoryValueRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<LaboratoryValueDTO> {
        const response = await this.createNewLaboratoryValueRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     */
    async deleteLaboratoryValueRaw(requestParameters: DeleteLaboratoryValueRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<void>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling deleteLaboratoryValue.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/LaboratoryValues/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'DELETE',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.VoidApiResponse(response);
    }

    /**
     */
    async deleteLaboratoryValue(requestParameters: DeleteLaboratoryValueRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<void> {
        await this.deleteLaboratoryValueRaw(requestParameters, initOverrides);
    }

    /**
     */
    async getAllLaboratoryValuesForLaboratoryReportRaw(requestParameters: GetAllLaboratoryValuesForLaboratoryReportRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Array<LaboratoryValueDTO>>> {
        if (requestParameters.laboratoryReportId === null || requestParameters.laboratoryReportId === undefined) {
            throw new runtime.RequiredError('laboratoryReportId','Required parameter requestParameters.laboratoryReportId was null or undefined when calling getAllLaboratoryValuesForLaboratoryReport.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/LaboratoryValues/{laboratoryReportId}`.replace(`{${"laboratoryReportId"}}`, encodeURIComponent(String(requestParameters.laboratoryReportId))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(LaboratoryValueDTOFromJSON));
    }

    /**
     */
    async getAllLaboratoryValuesForLaboratoryReport(requestParameters: GetAllLaboratoryValuesForLaboratoryReportRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Array<LaboratoryValueDTO>> {
        const response = await this.getAllLaboratoryValuesForLaboratoryReportRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     */
    async getAllLaboratoryValuesForLaboratoryReportWithNameRaw(requestParameters: GetAllLaboratoryValuesForLaboratoryReportWithNameRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Array<LaboratoryValueDTO>>> {
        if (requestParameters.laboratoryReportId === null || requestParameters.laboratoryReportId === undefined) {
            throw new runtime.RequiredError('laboratoryReportId','Required parameter requestParameters.laboratoryReportId was null or undefined when calling getAllLaboratoryValuesForLaboratoryReportWithName.');
        }

        if (requestParameters.laboratoryValueNameId === null || requestParameters.laboratoryValueNameId === undefined) {
            throw new runtime.RequiredError('laboratoryValueNameId','Required parameter requestParameters.laboratoryValueNameId was null or undefined when calling getAllLaboratoryValuesForLaboratoryReportWithName.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/LaboratoryValues/{laboratoryReportId}/{laboratoryValueNameId}`.replace(`{${"laboratoryReportId"}}`, encodeURIComponent(String(requestParameters.laboratoryReportId))).replace(`{${"laboratoryValueNameId"}}`, encodeURIComponent(String(requestParameters.laboratoryValueNameId))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(LaboratoryValueDTOFromJSON));
    }

    /**
     */
    async getAllLaboratoryValuesForLaboratoryReportWithName(requestParameters: GetAllLaboratoryValuesForLaboratoryReportWithNameRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Array<LaboratoryValueDTO>> {
        const response = await this.getAllLaboratoryValuesForLaboratoryReportWithNameRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     */
    async updateLaboratoryValueRaw(requestParameters: UpdateLaboratoryValueRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<LaboratoryValueDTO>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling updateLaboratoryValue.');
        }

        if (requestParameters.laboratoryValueRequest === null || requestParameters.laboratoryValueRequest === undefined) {
            throw new runtime.RequiredError('laboratoryValueRequest','Required parameter requestParameters.laboratoryValueRequest was null or undefined when calling updateLaboratoryValue.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/LaboratoryValues/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'PUT',
            headers: headerParameters,
            query: queryParameters,
            body: LaboratoryValueRequestToJSON(requestParameters.laboratoryValueRequest),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => LaboratoryValueDTOFromJSON(jsonValue));
    }

    /**
     */
    async updateLaboratoryValue(requestParameters: UpdateLaboratoryValueRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<LaboratoryValueDTO> {
        const response = await this.updateLaboratoryValueRaw(requestParameters, initOverrides);
        return await response.value();
    }

}
