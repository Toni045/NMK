package hr.fer.zpr.infsus.integration;

import hr.fer.zpr.infsus.domain.JPAEntities.Department;
import hr.fer.zpr.infsus.domain.JPAEntities.User;
import hr.fer.zpr.infsus.domain.JPAEntities.UserType;
import hr.fer.zpr.infsus.infrastructure.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail_WhenUserExists_ShouldReturnUser() {
        // Create a test user
        User user = new User();
        user.setId(10);
        user.setName("test");
        user.setEmail("test@example.com");
        UserType userType = new UserType();
        userType.setId(1);
        userType.setTypeName("USER");
        user.setUserType(userType);
        Department department = new Department();
        department.setId(1);
        department.setLocation("Zagreb");
        department.setName("department");
        department.setPurpose("purpose");
        user.setDepartment(department);
        userRepository.save(user);

        // Perform the findByEmail operation
        Optional<User> foundUser = userRepository.findByEmail("test@example.com");

        // Assertions
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        assertEquals(user.getName(), foundUser.get().getName());
        assertEquals(user.getUserType().getId(), foundUser.get().getUserType().getId());
        assertEquals(user.getUserType().getTypeName(), foundUser.get().getUserType().getTypeName());
    }

    @Test
    public void testFindByEmail_WhenUserDoesNotExist_ShouldReturnEmptyOptional() {
        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        assertTrue(foundUser.isEmpty());
    }
}