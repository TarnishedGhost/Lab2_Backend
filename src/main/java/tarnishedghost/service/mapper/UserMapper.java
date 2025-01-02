package tarnishedghost.service.mapper;

import tarnishedghost.domain.User;
import tarnishedghost.dto.user.UserEntryDto;
import tarnishedghost.dto.user.UserListDto;
import tarnishedghost.dto.user.request.LoginRequestDto;
import tarnishedghost.dto.user.request.RegisterRequestDto;
import tarnishedghost.structure.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User toUser(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "username", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(target = "categories", ignore = true)
    User toUser(RegisterRequestDto registerRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(target = "categories", ignore = true)
    User toUser(LoginRequestDto loginRequestDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "categories", target = "categories")
    UserEntity toUserEntity(User user);

    @Mapping(source = "name", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    RegisterRequestDto toRegisterUserDto(User user);

    default UserListDto toUserListDto(List<User> userDetails) {
        return UserListDto.builder().users(toUserEntry(userDetails)).build();
    }

    List<UserEntryDto> toUserEntry(List<User> users);

    default List<User> toUserList(Iterator<UserEntity> userServiceEntityIterator) {
        List<User> result = new ArrayList<>();
        userServiceEntityIterator.forEachRemaining(
                (cosmoCatEntity) -> {
                    result.add(toUser(cosmoCatEntity));
                });
        return result;
    }
}
