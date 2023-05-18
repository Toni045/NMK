package  hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LaboratoryValuesRepository extends JpaRepository<LaboratoryValues, Integer> {
    List<LaboratoryValues> findByLaboratoryReport_Id(Integer laboratoryReportId);
}
