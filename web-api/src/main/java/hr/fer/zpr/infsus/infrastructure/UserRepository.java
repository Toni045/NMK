package  hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
}
