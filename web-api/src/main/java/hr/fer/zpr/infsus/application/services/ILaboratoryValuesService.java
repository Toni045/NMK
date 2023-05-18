package hr.fer.zpr.infsus.application.services;

import hr.fer.zpr.infsus.domain.dto.LaboratoryValueDTO;
import hr.fer.zpr.infsus.domain.request.LaboratoryValueRequest;

import java.util.List;

public interface ILaboratoryValuesService {
    List<LaboratoryValueDTO> laboratoryValuesForLaboratoryReport(Integer laboratoryReportId);

    void deleteLaboratoryValue(Integer id);

    LaboratoryValueDTO updateLaboratoryValue(Integer id, LaboratoryValueRequest request);

    LaboratoryValueDTO createNewLaboratoryValue(Integer laboratoryReportId, LaboratoryValueRequest request);
}
