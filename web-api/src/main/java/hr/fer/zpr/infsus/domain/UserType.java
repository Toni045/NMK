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
public class UserType {

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
    private String typeName;

    @Column(nullable = false)
    private Integer userId;

    @OneToMany(mappedBy = "userType")
    private Set<User> userTypeUsers;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(final String typeName) {
        this.typeName = typeName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public Set<User> getUserTypeUsers() {
        return userTypeUsers;
    }

    public void setUserTypeUsers(final Set<User> userTypeUsers) {
        this.userTypeUsers = userTypeUsers;
    }

}
