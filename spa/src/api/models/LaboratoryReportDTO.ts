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
 * @interface LaboratoryReportDTO
 */
export interface LaboratoryReportDTO {
    /**
     * 
     * @type {number}
     * @memberof LaboratoryReportDTO
     */
    id?: number;
    /**
     * 
     * @type {Date}
     * @memberof LaboratoryReportDTO
     */
    date?: Date;
    /**
     * 
     * @type {string}
     * @memberof LaboratoryReportDTO
     */
    description?: string;
    /**
     * 
     * @type {string}
     * @memberof LaboratoryReportDTO
     */
    userEmail?: string;
    /**
     * 
     * @type {string}
     * @memberof LaboratoryReportDTO
     */
    userName?: string;
}

/**
 * Check if a given object implements the LaboratoryReportDTO interface.
 */
export function instanceOfLaboratoryReportDTO(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function LaboratoryReportDTOFromJSON(json: any): LaboratoryReportDTO {
    return LaboratoryReportDTOFromJSONTyped(json, false);
}

export function LaboratoryReportDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): LaboratoryReportDTO {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'id': !exists(json, 'id') ? undefined : json['id'],
        'date': !exists(json, 'date') ? undefined : (new Date(json['date'])),
        'description': !exists(json, 'description') ? undefined : json['description'],
        'userEmail': !exists(json, 'userEmail') ? undefined : json['userEmail'],
        'userName': !exists(json, 'userName') ? undefined : json['userName'],
    };
}

export function LaboratoryReportDTOToJSON(value?: LaboratoryReportDTO | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'id': value.id,
        'date': value.date === undefined ? undefined : (value.date.toISOString().substr(0,10)),
        'description': value.description,
        'userEmail': value.userEmail,
        'userName': value.userName,
    };
}

