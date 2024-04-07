package org.birthdayreminder.app.configuration;

import org.birthdayreminder.app.mapper.PersonBirthdayMapper;
import org.birthdayreminder.app.mapper.UserMapper;

import org.birthdayreminder.app.repository.PersonBirthdayEntityRepository;
import org.birthdayreminder.app.repository.UserEntityRepository;
import org.birthdayreminder.app.repository.impl.JPAPersonBirthdayRepositoryImpl;
import org.birthdayreminder.app.repository.impl.JPAUserRepositoryImpl;
import org.birthdayreminder.client.TelegramRestClient;
import org.birthdayreminder.client.TelegramRestClientSettings;
import org.birthdayreminder.client.impl.TelegramRestClientImpl;
import org.birthdayreminder.domain.repository.PersonBirthdayRepository;
import org.birthdayreminder.domain.repository.UserRepository;
import org.birthdayreminder.domain.service.UserBirthdayService;
import org.birthdayreminder.domain.service.impl.UserBirthdayServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Value("${telegram.bot.api}")
    private String api;
    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.timeOutSeconds}")
    private Integer timeOutSeconds;

    @Bean
    public TelegramRestClientSettings telegramRestClientSettings() {
        return new TelegramRestClientSettings(api, botToken, timeOutSeconds);
    }

    @Bean
    public TelegramRestClient telegramRestClient(TelegramRestClientSettings telegramRestClientSettings) {
        return new TelegramRestClientImpl(telegramRestClientSettings);
    }

    @Bean
    public UserBirthdayService userBirthdayService(UserRepository userRepository, PersonBirthdayRepository personBirthdayRepository) {
        return new UserBirthdayServiceImpl(userRepository, personBirthdayRepository);
    }

    @Bean
    public UserRepository userRepository(UserEntityRepository userRepo, UserMapper userMapper) {
        return new JPAUserRepositoryImpl(userRepo, userMapper);
    }

    @Bean
    public PersonBirthdayRepository personBirthdayRepository(PersonBirthdayEntityRepository birthdayRepo, PersonBirthdayMapper birthdayMapper, UserEntityRepository userRepo) {
        return new JPAPersonBirthdayRepositoryImpl(birthdayRepo, birthdayMapper, userRepo);
    }
}
