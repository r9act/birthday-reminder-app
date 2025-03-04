package org.birthdayreminder.app.mapper;

import org.birthdayreminder.app.PersonBirthdayDto;
import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.app.entity.PersonBirthdayEntity;
import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface PersonBirthdayMapper {
    @Mapping(source = "userEntity", target = "user")
    PersonBirthday toModel(PersonBirthdayEntity entity);
    @Mapping(source = "user", target = "userEntity")
    PersonBirthdayEntity toEntity(PersonBirthday model);
    @Mapping(source = "owner", target = "user")
    PersonBirthday toModel(PersonBirthdayDto personBirthdayDto);

    @Mapping(source = "user", target = "owner")
    PersonBirthdayDto toDto(PersonBirthday personBirthday);

    @Mapping(source = "foreignId", target = "foreignId")
    @Mapping(source = "name", target = "name")
    User toModel(UserDto userDto);

    @Mapping(source = "foreignId", target = "foreignId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "isReminderActive", target = "isReminderActive")
    UserDto toDto(User user);
}
