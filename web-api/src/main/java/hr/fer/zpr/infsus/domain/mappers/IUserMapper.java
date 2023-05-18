package hr.fer.zpr.infsus.domain.mappers;

import hr.fer.zpr.infsus.domain.JPAEntities.User;
import hr.fer.zpr.infsus.domain.dto.UserDropdownDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserDropdownDTO userToUserDropdownDTO(User user);
    List<UserDropdownDTO> userToUserDropdownDTO(List<User> user);
}
