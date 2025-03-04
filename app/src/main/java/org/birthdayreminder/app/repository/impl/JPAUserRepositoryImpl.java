package org.birthdayreminder.app.repository.impl;

import org.birthdayreminder.app.mapper.UserMapper;
import org.birthdayreminder.app.repository.UserEntityRepository;
import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JPAUserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userRepo;
    private final UserMapper userMapper;

    public JPAUserRepositoryImpl(UserEntityRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public void updateUser(User user) {
        user.setIsReminderActive(!user.getIsReminderActive());
        userRepo.save(userMapper.toEntity(user));
    }

    @Override
    public Boolean userExists(User user) {
       return userRepo.existsByForeignId(user.getForeignId());
    }

    /**
     * Проверяет, есть ли такой юзер, если да - вернет его foreignId, нет - сохранит. Для API проверка будет в сервисе.
	 */
    @Override
    @Deprecated
    public Long saveNewUser(User user) {
        if (!userExists(user)) {
            userRepo.save(userMapper.toEntity(user));
            return user.getId();
        }
        return getUserByForeignId(user.getForeignId()).orElseThrow(NullPointerException::new).getId();
    }

    @Override public Long saveUser(User user) {
        userRepo.save(userMapper.toEntity(user));
        return user.getId();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id).map(userMapper::toModel);
    }

    @Override
    public Optional<User> getUserByForeignId(Long foreignId) {
        return userRepo.findByForeignId(foreignId).map(userMapper::toModel);
    }
}
