package hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LaboratoryReportRepository extends JpaRepository<LaboratoryReport, Integer> {
    List<LaboratoryReport> findByUser_Id(Integer userId);
}
