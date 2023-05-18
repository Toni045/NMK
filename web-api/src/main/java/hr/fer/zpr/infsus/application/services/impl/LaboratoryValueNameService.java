package hr.fer.zpr.infsus.application.services.impl;

import hr.fer.zpr.infsus.application.services.ILaboratoryValueNameService;
import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryValueName;
import hr.fer.zpr.infsus.domain.dto.LaboratoryValueNameDropdownDTO;
import hr.fer.zpr.infsus.domain.mappers.ILaboratoryValueNameMapper;
import hr.fer.zpr.infsus.infrastructure.LaboratoryValueNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public LaboratoryValueNameDropdownDTO updateLaboratoryValueName(Integer id, String laboratoryValueName) {
        Optional<LaboratoryValueName> optionalLaboratoryValueName=laboratoryValueNameRepository.findById(id);
        if(optionalLaboratoryValueName.isEmpty()){
            return null;
        }
        LaboratoryValueName laboratoryValueName1=optionalLaboratoryValueName.get();
        laboratoryValueName1.setName(laboratoryValueName);
        return laboratoryValueNameMapper.laboratoryValueNameToDropdownDTO(laboratoryValueNameRepository.save(laboratoryValueName1));
    }

    @Override
    public void deleteLaboratoryValueName(Integer id) {
        laboratoryValueNameRepository.deleteById(id);
    }

    @Override
    public LaboratoryValueNameDropdownDTO createLaboratoryValueName(String name) {
        LaboratoryValueName newLaboratoryValueName=new LaboratoryValueName();
        newLaboratoryValueName.setName(name);
        return laboratoryValueNameMapper.laboratoryValueNameToDropdownDTO(laboratoryValueNameRepository.save(newLaboratoryValueName));
    }
}
