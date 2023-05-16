package hr.fer.zpr.infsus.domain;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Reservation {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private LocalDate start;

    @Column(nullable = false, name = "\"end\"")
    private LocalDate end;

    @ManyToOne
    @JoinColumn(name = "timeslot_id", nullable = false)
    private Timeslot timeslot;

    public Reservation() {
    }

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

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(final Timeslot timeslot) {
        this.timeslot = timeslot;
    }

}
