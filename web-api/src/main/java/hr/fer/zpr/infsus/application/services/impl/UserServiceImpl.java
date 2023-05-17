package hr.fer.zpr.infsus.application.services.impl;

import hr.fer.zpr.infsus.application.services.IUserService;
import hr.fer.zpr.infsus.domain.dto.UserDropdownDTO;
import hr.fer.zpr.infsus.domain.mappers.IUserMapper;
import hr.fer.zpr.infsus.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(IUserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDropdownDTO> getUsersAsDropdown() {
        return userMapper.userToUserDropdownDTO(userRepository.findAll());
    }
}
