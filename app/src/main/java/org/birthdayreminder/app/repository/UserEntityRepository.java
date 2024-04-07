package org.birthdayreminder.app.repository;

import org.birthdayreminder.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByChatId(Long chatId);

    Optional<UserEntity> findByChatId(Long chatId);
}
