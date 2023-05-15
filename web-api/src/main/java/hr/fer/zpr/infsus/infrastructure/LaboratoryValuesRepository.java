package  hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.LaboratoryValues;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LaboratoryValuesRepository extends JpaRepository<LaboratoryValues, Integer> {
}
