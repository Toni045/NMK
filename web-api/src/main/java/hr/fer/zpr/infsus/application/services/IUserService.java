package hr.fer.zpr.infsus.application.services;

import hr.fer.zpr.infsus.domain.dto.UserDropdownDTO;

import java.util.List;

public interface IUserService {
    List<UserDropdownDTO> getUsersAsDropdown();
}
