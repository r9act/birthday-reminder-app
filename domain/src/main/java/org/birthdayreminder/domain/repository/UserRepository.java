package org.birthdayreminder.domain.repository;

import org.birthdayreminder.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    void updateUser(User user);

    Boolean userExists(User user);

    Long saveNewUser(User user);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByChatId(Long chatId);

    //использовался в class ReminderTask для возвращения списка User для рассылки напоминаний (переделано)
  //  Set<User> getSetOfUsersByListOfId(Set<Long> listOfOwnerchatIds);
}
