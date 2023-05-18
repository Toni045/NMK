package hr.fer.zpr.infsus.domain.mappers;

import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryValueName;
import hr.fer.zpr.infsus.domain.dto.LaboratoryValueNameDropdownDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ILaboratoryValueNameMapper {
    LaboratoryValueNameDropdownDTO laboratoryValueNameToDropdownDTO(LaboratoryValueName laboratoryValueName);

    List<LaboratoryValueNameDropdownDTO> laboratoryValueNamesToDropdownDTOs(List<LaboratoryValueName> laboratoryValueNames);
}
