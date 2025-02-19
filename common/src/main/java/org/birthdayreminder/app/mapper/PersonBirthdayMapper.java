package org.birthdayreminder.app.mapper;

import org.birthdayreminder.app.entity.PersonBirthdayEntity;
import org.birthdayreminder.domain.model.PersonBirthday;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface PersonBirthdayMapper {
    @Mapping(source = "userEntity", target = "user")
    PersonBirthday toModel(PersonBirthdayEntity entity);
    @Mapping(source = "user", target = "userEntity")
    PersonBirthdayEntity toEntity(PersonBirthday model);
}
