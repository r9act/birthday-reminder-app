package org.birthdayreminder.app.service;

import org.birthdayreminder.client.TelegramRestClient;
import org.birthdayreminder.domain.model.DatePeriod;
import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.domain.repository.PersonBirthdayRepository;
import org.birthdayreminder.domain.service.impl.BirthdayListPrinter;
import org.birthdayreminder.dto.out.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReminderTask {

    private final PersonBirthdayRepository personBirthdayRepository;
    private final TelegramRestClient client;

    public ReminderTask(PersonBirthdayRepository personBirthdayRepository, TelegramRestClient client) {
        this.personBirthdayRepository = personBirthdayRepository;
        this.client = client;
    }

    public void runReminder() {
        DatePeriod datePeriod = new DatePeriod(LocalDate.now(), LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1));

        List<PersonBirthday> personBirthdaysList = personBirthdayRepository.getAllBirthdaysIn(datePeriod);

        Set<User> listOfOwnerChatIds = personBirthdaysList.stream().map(PersonBirthday::user).
                collect(Collectors.toSet());

        Set<User> listOfUsersWithReminderActive = listOfOwnerChatIds
                .stream()
                .filter(User::getIsReminderActive)
                .collect(Collectors.toSet());

        for (User u : listOfUsersWithReminderActive) {
            var chatId = u.getChatId();
            List<PersonBirthday> listToSend = personBirthdaysList.stream().filter(personBirthday -> personBirthday.user().equals(u)).toList();
            client.sendMessage(new Message(chatId, BirthdayListPrinter.printList(listToSend)));
        }
    }
}
