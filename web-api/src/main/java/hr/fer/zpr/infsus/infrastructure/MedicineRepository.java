package  hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}
