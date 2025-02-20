package org.birthdayreminder.app.mapper;

import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.app.entity.UserEntity;
import org.birthdayreminder.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserEntity entity);

    UserEntity toEntity(User model);
    @Mapping(source = "androidChatId", target = "chatId")
    @Mapping(source = "firstName", target = "name")
    User toModel(UserDto userDto);

    UserEntity toEntity(UserDto userDto);
    @Mapping(source = "chatId", target = "androidChatId")
    @Mapping(source = "name", target = "firstName")
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true) // Ensure ID is NOT mapped
    void updateUserFromDto(UserDto dto, @MappingTarget User user);


}
