package hr.fer.zpr.infsus.application.services;

import hr.fer.zpr.infsus.domain.dto.LaboratoryReportDTO;
import hr.fer.zpr.infsus.domain.request.LaboratoryReportRequest;

import java.util.List;

public interface ILaboratoryReportService {
    List<LaboratoryReportDTO> getAllReports();

    LaboratoryReportDTO updateLaboratoryReport(LaboratoryReportRequest updateRequest);

    LaboratoryReportDTO createLaboratoryReport(LaboratoryReportRequest updateRequest);

    void deleteLaboratoryReport(int id);
}
