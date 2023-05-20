package hr.fer.zpr.infsus.application.services.impl;

import hr.fer.zpr.infsus.application.services.ILaboratoryValuesService;
import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryReport;
import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryValueName;
import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryValues;
import hr.fer.zpr.infsus.domain.dto.LaboratoryValueDTO;
import hr.fer.zpr.infsus.domain.mappers.ILaboratoryValueMapper;
import hr.fer.zpr.infsus.domain.request.LaboratoryValueRequest;
import hr.fer.zpr.infsus.infrastructure.LaboratoryReportRepository;
import hr.fer.zpr.infsus.infrastructure.LaboratoryValueNameRepository;
import hr.fer.zpr.infsus.infrastructure.LaboratoryValuesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratoryValuesServiceImpl implements ILaboratoryValuesService {
    private final LaboratoryValuesRepository laboratoryValuesRepository;
    private final ILaboratoryValueMapper laboratoryValueMapper;
    private final LaboratoryValueNameRepository laboratoryValueNameRepository;
    private final LaboratoryReportRepository laboratoryReportRepository;

    public LaboratoryValuesServiceImpl(LaboratoryValuesRepository laboratoryValuesRepository, ILaboratoryValueMapper laboratoryValueMapper, LaboratoryValueNameRepository laboratoryValueNameRepository, LaboratoryReportRepository laboratoryReportRepository) {
        this.laboratoryValuesRepository = laboratoryValuesRepository;
        this.laboratoryValueMapper = laboratoryValueMapper;
        this.laboratoryValueNameRepository = laboratoryValueNameRepository;
        this.laboratoryReportRepository = laboratoryReportRepository;
    }

    @Override
    public List<LaboratoryValueDTO> laboratoryValuesForLaboratoryReport(Integer laboratoryReportId) {
        return laboratoryValueMapper.laboratoryValuesToLaboratoryValueDTOs(laboratoryValuesRepository.findByLaboratoryReport_Id(laboratoryReportId));
    }

    @Override
    public void deleteLaboratoryValue(Integer id) {
        laboratoryValuesRepository.deleteById(id);
    }

    @Override
    public LaboratoryValueDTO updateLaboratoryValue(Integer id, LaboratoryValueRequest request) {
        Optional<LaboratoryValues> optionalLaboratoryValue = laboratoryValuesRepository.findById(id);
        if (optionalLaboratoryValue.isEmpty()) {
            return null;
        }
        LaboratoryValues laboratoryValue = optionalLaboratoryValue.get();
        if (request.getValue() != null) {
            laboratoryValue.setValue(request.getValue());
        }
        if (request.getLaboratoryValueNameId() != null) {
            Optional<LaboratoryValueName> sif = laboratoryValueNameRepository.findById(request.getLaboratoryValueNameId());
            if (sif.isEmpty()) {
                return null;
            }
            laboratoryValue.setLaboratoryValueName(sif.get());
        }
        laboratoryValuesRepository.save(laboratoryValue);
        return laboratoryValueMapper.laboratoryValueToLaboratoryValueDTO(laboratoryValue);
    }

    @Override
    public LaboratoryValueDTO createNewLaboratoryValue(Integer laboratoryReportId, LaboratoryValueRequest request) {
        if (request.getLaboratoryValueNameId() == null || request.getValue() == null) {
            return null;
        }
        Optional<LaboratoryReport> optionalLaboratoryReport = laboratoryReportRepository.findById(laboratoryReportId);
        if (optionalLaboratoryReport.isEmpty()) {
            return null;
        }
        Optional<LaboratoryValueName> optionalLaboratoryValueName = laboratoryValueNameRepository.findById(request.getLaboratoryValueNameId());
        if (optionalLaboratoryValueName.isEmpty()) {
            return null;
        }
        LaboratoryValues laboratoryValue = new LaboratoryValues();
        laboratoryValue.setLaboratoryReport(optionalLaboratoryReport.get());
        laboratoryValue.setValue(request.getValue());
        laboratoryValue.setLaboratoryValueName(optionalLaboratoryValueName.get());
        return laboratoryValueMapper.laboratoryValueToLaboratoryValueDTO(laboratoryValuesRepository.save(laboratoryValue));
    }

    @Override
    public List<LaboratoryValueDTO> getAllLaboratoryValuesForLaboratoryReportWithName(Integer laboratoryReportId, Integer laboratoryValueNameId) {
        return laboratoryValueMapper.laboratoryValuesToLaboratoryValueDTOs(laboratoryValuesRepository.findByLaboratoryReport_IdAndLaboratoryValueName_Id(laboratoryReportId, laboratoryValueNameId));
    }
}
