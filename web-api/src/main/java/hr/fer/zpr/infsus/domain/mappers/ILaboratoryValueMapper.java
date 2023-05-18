package hr.fer.zpr.infsus.domain.mappers;

import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryValues;
import hr.fer.zpr.infsus.domain.dto.LaboratoryValueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ILaboratoryValueMapper {
    @Mapping(target = "laboratoryReportId", source = "laboratoryReport.id")
    @Mapping(target = "laboratoryValueNameId", source = "laboratoryValueName.id")
    @Mapping(target = "laboratoryValueName", source = "laboratoryValueName.name")
    LaboratoryValueDTO laboratoryValueToLaboratoryValueDTO(LaboratoryValues laboratoryValue);

    List<LaboratoryValueDTO> laboratoryValuesToLaboratoryValueDTOs(List<LaboratoryValues> laboratoryValue);
}
