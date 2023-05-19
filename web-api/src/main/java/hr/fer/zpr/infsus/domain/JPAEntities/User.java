package hr.fer.zpr.infsus.domain.JPAEntities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 500)
    private String name;

    @Column(nullable = false, length = 500, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Timeslot> userTimeslots;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<LaboratoryReport> userLaboratoryReports;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Medicine> userMedicines;

    public User(String name, String email, UserType userType) {
        this.name = name;
        this.email = email;
        this.userType = userType;
    }

    public User() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public Set<Medicine> getUserMedicines() {
        return userMedicines;
    }

    public void setUserMedicines(final Set<Medicine> userMedicines) {
        this.userMedicines = userMedicines;
    }

}
