package hr.fer.zpr.infsus.domain;

import jakarta.persistence.*;


@Entity
public class LaboratoryValues {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 25)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laboratory_report_id", nullable = false)
    private LaboratoryReport laboratoryReport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laboratory_value_name_id", nullable = false)
    private LaboratoryValueName laboratoryValueName;

    public LaboratoryValues() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public LaboratoryReport getLaboratoryReport() {
        return laboratoryReport;
    }

    public void setLaboratoryReport(final LaboratoryReport laboratoryReport) {
        this.laboratoryReport = laboratoryReport;
    }

    public LaboratoryValueName getLaboratoryValueName() {
        return laboratoryValueName;
    }

    public void setLaboratoryValueName(final LaboratoryValueName laboratoryValueName) {
        this.laboratoryValueName = laboratoryValueName;
    }

}
