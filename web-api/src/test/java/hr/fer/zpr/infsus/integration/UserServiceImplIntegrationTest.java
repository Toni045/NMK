package hr.fer.zpr.infsus.integration;

import hr.fer.zpr.infsus.application.services.IUserService;
import hr.fer.zpr.infsus.application.services.impl.UserServiceImpl;
import hr.fer.zpr.infsus.domain.JPAEntities.User;
import hr.fer.zpr.infsus.domain.dto.UserDTO;
import hr.fer.zpr.infsus.domain.dto.UserDropdownDTO;
import hr.fer.zpr.infsus.domain.mappers.IUserMapper;
import hr.fer.zpr.infsus.infrastructure.UserRepository;
import hr.fer.zpr.infsus.infrastructure.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceImplIntegrationTest {

    private IUserService userService;

    @Mock
    private IUserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserTypeRepository userTypeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userMapper, userRepository, userTypeRepository);
    }

    @Test
    public void testGetUsersAsDropdown() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        List<UserDropdownDTO> userDropdownDTOs = new ArrayList<>();
        userDropdownDTOs.add(new UserDropdownDTO());
        when(userMapper.userToUserDropdownDTO(users)).thenReturn(userDropdownDTOs);

        List<UserDropdownDTO> result = userService.getUsersAsDropdown();

        assertNotNull(result);
        assertEquals(userDropdownDTOs.size(), result.size());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).userToUserDropdownDTO(users);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> userDTOs = new ArrayList<>();
        userDTOs.add(new UserDTO());
        when(userMapper.usersToUserDTOs(users)).thenReturn(userDTOs);

        List<UserDTO> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(userDTOs.size(), result.size());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).usersToUserDTOs(users);
    }
}
