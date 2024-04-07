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
       return userRepo.existsByChatId(user.getChatId());
    }

    @Override
    public Long saveNewUser(User user) {
        if (!userExists(user)) {
            userRepo.save(userMapper.toEntity(user));
            return user.getId();
        }
        return getUserByChatId(user.getChatId()).orElseThrow(NullPointerException::new).getId();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id).map(userMapper::toModel);
    }

    @Override
    public Optional<User> getUserByChatId(Long chatId) {
        return userRepo.findByChatId(chatId).map(userMapper::toModel);
    }
}
