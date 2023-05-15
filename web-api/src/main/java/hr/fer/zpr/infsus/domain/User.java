package hr.fer.zpr.infsus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "\"user\"")
public class User {

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

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String surname;

    @Column(nullable = false, length = 20)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "user")
    private Set<Timeslot> userTimeslots;

    @OneToMany(mappedBy = "user")
    private Set<LaboratoryReport> userLaboratoryReports;

    @OneToMany(mappedBy = "user")
    private Set<Medicine> userMedicines;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(final Department department) {
        this.department = department;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(final UserType userType) {
        this.userType = userType;
    }

    public Set<Timeslot> getUserTimeslots() {
        return userTimeslots;
    }

    public void setUserTimeslots(final Set<Timeslot> userTimeslots) {
        this.userTimeslots = userTimeslots;
    }

    public Set<LaboratoryReport> getUserLaboratoryReports() {
        return userLaboratoryReports;
    }

    public void setUserLaboratoryReports(final Set<LaboratoryReport> userLaboratoryReports) {
        this.userLaboratoryReports = userLaboratoryReports;
    }

    public Set<Medicine> getUserMedicines() {
        return userMedicines;
    }

    public void setUserMedicines(final Set<Medicine> userMedicines) {
        this.userMedicines = userMedicines;
    }

}
