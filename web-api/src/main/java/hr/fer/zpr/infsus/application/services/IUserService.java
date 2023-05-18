package hr.fer.zpr.infsus.application.services;

import hr.fer.zpr.infsus.domain.JPAEntities.EUserType;
import hr.fer.zpr.infsus.domain.dto.UserDTO;
import hr.fer.zpr.infsus.domain.dto.UserDropdownDTO;

import java.util.List;

public interface IUserService {
    List<UserDropdownDTO> getUsersAsDropdown();

    UserDTO getCurrentUser();

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Integer userId, Integer newType);
}
