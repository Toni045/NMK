package  hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.LaboratoryReport;
import hr.fer.zpr.infsus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LaboratoryReportRepository extends JpaRepository<LaboratoryReport, Integer> {
    List<LaboratoryReport> findByUser(User user);
}
