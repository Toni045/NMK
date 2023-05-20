package hr.fer.zpr.infsus.domain.mappers;

import hr.fer.zpr.infsus.domain.JPAEntities.User;
import hr.fer.zpr.infsus.domain.dto.UserDTO;
import hr.fer.zpr.infsus.domain.dto.UserDropdownDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserDropdownDTO userToUserDropdownDTO(User user);

    List<UserDropdownDTO> userToUserDropdownDTO(List<User> user);

    @Mapping(target = "userType", source = "userType.typeName")
    @Mapping(target = "userTypeId", source = "userType.id")
    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUserDTOs(List<User> user);
}
