package org.birthdayreminder.handlers.impl;

import org.birthdayreminder.handlers.MessageHandler;
import org.birthdayreminder.handlers.ReminderHandler;
import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.domain.repository.PersonBirthdayRepository;
import org.birthdayreminder.domain.repository.UserRepository;
import org.birthdayreminder.domain.service.PersonBirthdayFileParser;
import org.birthdayreminder.domain.service.UserBirthdayService;
import org.birthdayreminder.domain.service.impl.BirthdayListPrinter;
import org.birthdayreminder.domain.service.impl.PersonBirthdayExcelFileParser;
import org.birthdayreminder.dto.in.ResultDTO;
import org.birthdayreminder.dto.out.Message;

import org.birthdayreminder.utils.MenuCreator;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class MessageHandlerImpl implements MessageHandler {

    private final UserRepository userRepository;
    private final PersonBirthdayRepository personBirthdayRepository;
    private final ReminderHandler reminderHandler;
    private final UserBirthdayService userBirthdayService;

    public MessageHandlerImpl(UserRepository userRepository, PersonBirthdayRepository personBirthdayRepository, ReminderHandler reminderHandler, UserBirthdayService userBirthdayService) {
        this.userRepository = userRepository;
        this.personBirthdayRepository = personBirthdayRepository;
        this.reminderHandler = reminderHandler;
        this.userBirthdayService = userBirthdayService;
    }

    @Override
    public Message handleTextMessage(ResultDTO result) {

        var chatId = result.getMessage().getUser().getChatId();
        var command = result.getMessage().getText();

        var userId = userRepository
                .saveNewUser(new User(chatId, result.getMessage().getUser().getFirstName(), false));
        switch (command) {
            case "/start" -> {
                return new Message(chatId, "Upload xlsx to continue...", MenuCreator.getMainMenuKeyboard());
            }
            case "Show all birthdays" -> {
                if (!personBirthdayRepository.getAllByUserId(userId).isEmpty()) {
                    return new Message(chatId, BirthdayListPrinter.printList(personBirthdayRepository.getAllByUserId(userId)));
                } else return new Message(chatId, "List is still empty. Use /start command.");
            }
            case "Select month to show birthdays" -> {
                return new Message(chatId, "Choose  month:", MenuCreator.getInlineKeyboardMonth());
            }
            case "On/off reminder" -> {
                reminderHandler.onReminder(result);
                return new Message(chatId, "Changes saved!");
            }
            default -> {
                return new Message(chatId, "Wrong command");
            }
        }
    }

    @Override
    public Message handleDocumentMessage(InputStream is, ResultDTO result) {
        PersonBirthdayFileParser parser = new PersonBirthdayExcelFileParser();
        var chatId = result.getMessage().getUser().getChatId();

        return userRepository.getUserByChatId(chatId)
                .map(User::getId)
                .map(id -> userBirthdayService.linkBirthdayToUser(parser.parse(is), id))
                .map(sm -> new Message(chatId, "Success!"))
                .orElseGet(() -> new Message(chatId, "Register first!"));
    }
}



