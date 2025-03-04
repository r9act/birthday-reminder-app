package org.birthdayreminder.app.mapper;

import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.app.entity.UserEntity;
import org.birthdayreminder.domain.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserEntity entity);

    UserEntity toEntity(User model);
    @Mapping(source = "foreignId", target = "foreignId")
    @Mapping(source = "name", target = "name")
    User toModel(UserDto userDto);

    UserEntity toEntity(UserDto userDto);
    @Mapping(source = "foreignId", target = "foreignId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "isReminderActive", target = "isReminderActive")
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true) // PK оставляем нетронутым
    @Mapping(source = "foreignId", target = "foreignId")
    @Mapping(source = "isReminderActive", target = "isReminderActive")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // Не перезаписываем null
    void updateUserFromDto(UserDto dto, @MappingTarget User user);
}
