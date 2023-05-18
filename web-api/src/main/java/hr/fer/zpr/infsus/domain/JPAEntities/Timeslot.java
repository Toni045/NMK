package hr.fer.zpr.infsus.domain.JPAEntities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
public class Timeslot {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private LocalDate start;

    @Column(nullable = false, name = "\"end\"")
    private LocalDate end;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Timeslot() {
    }

    @OneToMany(mappedBy = "timeslot")
    private Set<Reservation> timeslotReservations;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(final LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(final LocalDate end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Set<Reservation> getTimeslotReservations() {
        return timeslotReservations;
    }

    public void setTimeslotReservations(final Set<Reservation> timeslotReservations) {
        this.timeslotReservations = timeslotReservations;
    }

}
