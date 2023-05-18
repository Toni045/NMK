package hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryValueName;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LaboratoryValueNameRepository extends JpaRepository<LaboratoryValueName, Integer> {
}
