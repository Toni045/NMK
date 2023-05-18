package hr.fer.zpr.infsus.application.services.impl;

import hr.fer.zpr.infsus.application.services.ILaboratoryValueNameService;
import hr.fer.zpr.infsus.domain.dto.LaboratoryValueNameDropdownDTO;
import hr.fer.zpr.infsus.domain.mappers.ILaboratoryValueNameMapper;
import hr.fer.zpr.infsus.infrastructure.LaboratoryValueNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryValueNameService implements ILaboratoryValueNameService {
    private final LaboratoryValueNameRepository laboratoryValueNameRepository;
    private final ILaboratoryValueNameMapper laboratoryValueNameMapper;

    public LaboratoryValueNameService(LaboratoryValueNameRepository laboratoryValueNameRepository, ILaboratoryValueNameMapper laboratoryValueNameMapper) {
        this.laboratoryValueNameRepository = laboratoryValueNameRepository;
        this.laboratoryValueNameMapper = laboratoryValueNameMapper;
    }

    @Override
    public List<LaboratoryValueNameDropdownDTO> getLaboratoryValueNameDropdown() {
        return laboratoryValueNameMapper.laboratoryValueNamesToDropdownDTOs(laboratoryValueNameRepository.findAll());
    }
}
