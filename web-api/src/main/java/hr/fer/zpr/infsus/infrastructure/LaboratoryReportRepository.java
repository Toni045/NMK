package  hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.LaboratoryReport;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LaboratoryReportRepository extends JpaRepository<LaboratoryReport, Integer> {
}
