package hr.fer.zpr.infsus.application.services.impl;

import hr.fer.zpr.infsus.application.services.IUserService;
import hr.fer.zpr.infsus.domain.JPAEntities.User;
import hr.fer.zpr.infsus.domain.JPAEntities.UserType;
import hr.fer.zpr.infsus.domain.dto.UserDTO;
import hr.fer.zpr.infsus.domain.dto.UserDropdownDTO;
import hr.fer.zpr.infsus.domain.mappers.IUserMapper;
import hr.fer.zpr.infsus.infrastructure.UserRepository;
import hr.fer.zpr.infsus.infrastructure.UserTypeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserMapper userMapper;
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    public UserServiceImpl(IUserMapper userMapper, UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public List<UserDropdownDTO> getUsersAsDropdown() {
        return userMapper.userToUserDropdownDTO(userRepository.findAll());
    }

    @Override
    public UserDTO getCurrentUser() {
        Object authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authenticatedUser instanceof String) {
            return null;
        }
        UserDetails userDetails = (UserDetails) authenticatedUser;
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        if (user.isEmpty()) {
            return null;
        }
        return userMapper.userToUserDTO(user.get());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userMapper.usersToUserDTOs(userRepository.findAll());
    }

    @Override
    public UserDTO updateUser(Integer userId, Integer newType) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        Optional<UserType> optionalUserType = userTypeRepository.findById(newType);
        if (optionalUserType.isEmpty()) {
            return null;
        }
        user.setUserType(optionalUserType.get());
        return userMapper.userToUserDTO(userRepository.save(user));
    }
}
