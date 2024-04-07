package org.birthdayreminder.app.mapper;

import org.birthdayreminder.app.entity.UserEntity;
import org.birthdayreminder.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserEntity entity);

    UserEntity toEntity(User model);
}
