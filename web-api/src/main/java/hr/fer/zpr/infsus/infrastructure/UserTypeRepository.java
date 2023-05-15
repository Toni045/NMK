package  hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
