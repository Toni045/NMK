package  hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
