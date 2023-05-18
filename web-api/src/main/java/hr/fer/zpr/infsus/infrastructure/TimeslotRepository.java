package  hr.fer.zpr.infsus.infrastructure;

import hr.fer.zpr.infsus.domain.JPAEntities.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TimeslotRepository extends JpaRepository<Timeslot, Integer> {
}
