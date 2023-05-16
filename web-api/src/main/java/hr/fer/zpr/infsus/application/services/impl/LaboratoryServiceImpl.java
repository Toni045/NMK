package hr.fer.zpr.infsus.application.services.impl;

import hr.fer.zpr.infsus.application.services.ILaboratoryReportService;
import hr.fer.zpr.infsus.domain.dto.LaboratoryReportDTO;
import hr.fer.zpr.infsus.domain.mappers.ILaboratoryReportMapper;
import hr.fer.zpr.infsus.infrastructure.LaboratoryReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryServiceImpl implements ILaboratoryReportService {
    private final ILaboratoryReportMapper laboratoryReportMapper;
    private final LaboratoryReportRepository laboratoryReportRepository;

    public LaboratoryServiceImpl(ILaboratoryReportMapper laboratoryReportMapper, LaboratoryReportRepository laboratoryReportRepository) {
        this.laboratoryReportRepository = laboratoryReportRepository;
        this.laboratoryReportMapper = laboratoryReportMapper;
    }

    @Override
    public List<LaboratoryReportDTO> getAllReports() {
        return laboratoryReportMapper.laboratoryReportsToLaboratoryReportDTOs(laboratoryReportRepository.findAll());
    }
}
