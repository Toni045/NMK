package hr.fer.zpr.infsus.domain.JPAEntities;

import jakarta.persistence.*;

import java.util.Set;


@Entity
public class UserType {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 20, unique = true)
    private String typeName;

    @OneToMany(mappedBy = "userType", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<User> userTypeUsers;

    public UserType() {
    }

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

    public Set<User> getUserTypeUsers() {
        return userTypeUsers;
    }

    public void setUserTypeUsers(final Set<User> userTypeUsers) {
        this.userTypeUsers = userTypeUsers;
    }

}
