package hr.fer.zpr.infsus.application.services;

import hr.fer.zpr.infsus.domain.dto.LaboratoryValueDTO;

import java.util.List;

public interface ILaboratoryValuesService {
    List<LaboratoryValueDTO> laboratoryValuesForLaboratoryReport(Integer laboratoryReportId);
}
