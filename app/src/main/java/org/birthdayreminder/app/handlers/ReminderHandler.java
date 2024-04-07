package org.birthdayreminder.app.handlers;

import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.domain.repository.UserRepository;
import org.birthdayreminder.dto.in.ResultDTO;
import org.springframework.stereotype.Component;

@Component
public class ReminderHandler {

    private final UserRepository userRepository;

    public ReminderHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void onReminder(ResultDTO result) {
        User user = userRepository.getUserByChatId(result.getMessage().getUser().getChatId()).orElseThrow(NullPointerException::new);
        userRepository.updateUser(user);
    }
}
