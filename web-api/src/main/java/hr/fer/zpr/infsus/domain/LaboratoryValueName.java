package hr.fer.zpr.infsus.domain;

import jakarta.persistence.*;

import java.util.Set;


@Entity
public class LaboratoryValueName {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 25)
    private String name;

    @OneToMany(mappedBy = "laboratoryValueName")
    private Set<LaboratoryValues> laboratoryValueNameLaboratoryValuess;

    public LaboratoryValueName() {
    }

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
