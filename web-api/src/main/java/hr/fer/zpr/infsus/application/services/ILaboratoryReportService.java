package hr.fer.zpr.infsus.application.services;

import hr.fer.zpr.infsus.domain.dto.LaboratoryReportDTO;

import java.util.List;

public interface ILaboratoryReportService {
    List<LaboratoryReportDTO> getAllReports();
}
