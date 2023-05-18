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

import { exists, mapValues } from '../runtime';
/**
 * 
 * @export
 * @interface LaboratoryValueDTO
 */
export interface LaboratoryValueDTO {
    /**
     * 
     * @type {number}
     * @memberof LaboratoryValueDTO
     */
    id?: number;
    /**
     * 
     * @type {number}
     * @memberof LaboratoryValueDTO
     */
    value?: number;
    /**
     * 
     * @type {number}
     * @memberof LaboratoryValueDTO
     */
    laboratoryReportId?: number;
    /**
     * 
     * @type {number}
     * @memberof LaboratoryValueDTO
     */
    laboratoryValueNameId?: number;
    /**
     * 
     * @type {string}
     * @memberof LaboratoryValueDTO
     */
    laboratoryValueName?: string;
}

/**
 * Check if a given object implements the LaboratoryValueDTO interface.
 */
export function instanceOfLaboratoryValueDTO(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function LaboratoryValueDTOFromJSON(json: any): LaboratoryValueDTO {
    return LaboratoryValueDTOFromJSONTyped(json, false);
}

export function LaboratoryValueDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): LaboratoryValueDTO {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'id': !exists(json, 'id') ? undefined : json['id'],
        'value': !exists(json, 'value') ? undefined : json['value'],
        'laboratoryReportId': !exists(json, 'laboratoryReportId') ? undefined : json['laboratoryReportId'],
        'laboratoryValueNameId': !exists(json, 'laboratoryValueNameId') ? undefined : json['laboratoryValueNameId'],
        'laboratoryValueName': !exists(json, 'laboratoryValueName') ? undefined : json['laboratoryValueName'],
    };
}

export function LaboratoryValueDTOToJSON(value?: LaboratoryValueDTO | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'id': value.id,
        'value': value.value,
        'laboratoryReportId': value.laboratoryReportId,
        'laboratoryValueNameId': value.laboratoryValueNameId,
        'laboratoryValueName': value.laboratoryValueName,
    };
}

