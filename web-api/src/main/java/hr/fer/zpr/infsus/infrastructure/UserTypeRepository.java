package hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.JPAEntities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
    Optional<UserType> findByTypeName(String typeName);
}
