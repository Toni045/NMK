package hr.fer.zpr.infsus.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
public class LaboratoryReport {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public LaboratoryReport() {
    }

    @OneToMany(mappedBy = "laboratoryReport")
    private Set<LaboratoryValues> laboratoryReportLaboratoryValuess;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Set<LaboratoryValues> getLaboratoryReportLaboratoryValuess() {
        return laboratoryReportLaboratoryValuess;
    }

    public void setLaboratoryReportLaboratoryValuess(
            final Set<LaboratoryValues> laboratoryReportLaboratoryValuess) {
        this.laboratoryReportLaboratoryValuess = laboratoryReportLaboratoryValuess;
    }

}
