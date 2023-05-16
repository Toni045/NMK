package hr.fer.zpr.infsus.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Department {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String purpose;

    @Column(nullable = false, length = 50)
    private String location;

    public Department() {
    }

    @OneToMany(mappedBy = "department")
    private Set<User> departmentUsers;

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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(final String purpose) {
        this.purpose = purpose;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public Set<User> getDepartmentUsers() {
        return departmentUsers;
    }

    public void setDepartmentUsers(final Set<User> departmentUsers) {
        this.departmentUsers = departmentUsers;
    }

}
