package hr.fer.zpr.infsus.domain.mappers;

import hr.fer.zpr.infsus.domain.LaboratoryReport;
import hr.fer.zpr.infsus.domain.dto.LaboratoryReportDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ILaboratoryReportMapper {
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "userId", source = "user.id")
    LaboratoryReportDTO laboratoryReportToLaboratoryReportDTO(LaboratoryReport laboratoryReport);

    List<LaboratoryReportDTO> laboratoryReportsToLaboratoryReportDTOs(List<LaboratoryReport> laboratoryReports);
}
