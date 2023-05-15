package hr.fer.zpr.infsus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.Set;


@Entity
public class LaboratoryValueName {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(nullable = false, length = 25)
    private String name;

    @OneToMany(mappedBy = "laboratoryValueName")
    private Set<LaboratoryValues> laboratoryValueNameLaboratoryValuess;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<LaboratoryValues> getLaboratoryValueNameLaboratoryValuess() {
        return laboratoryValueNameLaboratoryValuess;
    }

    public void setLaboratoryValueNameLaboratoryValuess(
            final Set<LaboratoryValues> laboratoryValueNameLaboratoryValuess) {
        this.laboratoryValueNameLaboratoryValuess = laboratoryValueNameLaboratoryValuess;
    }

}
