package hr.fer.zpr.infsus.application.services.impl;

import hr.fer.zpr.infsus.application.services.ILaboratoryValuesService;
import hr.fer.zpr.infsus.domain.dto.LaboratoryValueDTO;
import hr.fer.zpr.infsus.domain.mappers.ILaboratoryReportMapper;
import hr.fer.zpr.infsus.domain.mappers.ILaboratoryValueMapper;
import hr.fer.zpr.infsus.infrastructure.LaboratoryValuesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryValuesServiceImpl implements ILaboratoryValuesService {
    private final LaboratoryValuesRepository laboratoryValuesRepository;
    private final ILaboratoryValueMapper laboratoryValueMapper;

    public LaboratoryValuesServiceImpl(LaboratoryValuesRepository laboratoryValuesRepository, ILaboratoryValueMapper laboratoryValueMapper) {
        this.laboratoryValuesRepository = laboratoryValuesRepository;
        this.laboratoryValueMapper = laboratoryValueMapper;
    }

    @Override
    public List<LaboratoryValueDTO> laboratoryValuesForLaboratoryReport(Integer laboratoryReportId) {
        return laboratoryValueMapper.laboratoryValuesToLaboratoryValueDTOs(laboratoryValuesRepository.findByLaboratoryReport_Id(laboratoryReportId));
    }
}
