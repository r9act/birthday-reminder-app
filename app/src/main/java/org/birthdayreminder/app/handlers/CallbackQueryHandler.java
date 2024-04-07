package org.birthdayreminder.app.handlers;

import org.birthdayreminder.domain.model.DatePeriod;
import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.domain.repository.PersonBirthdayRepository;
import org.birthdayreminder.domain.repository.UserRepository;
import org.birthdayreminder.domain.service.impl.BirthdayListPrinter;
import org.birthdayreminder.dto.in.ResultDTO;
import org.birthdayreminder.dto.out.Message;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.List;

@Component
public class CallbackQueryHandler {

    private final UserRepository userRepository;
    private final PersonBirthdayRepository personBirthdayRepository;
    private final Integer FIRSTDAYOFMONTH = 1;

    public CallbackQueryHandler(UserRepository userRepository, PersonBirthdayRepository personBirthdayRepository) {
        this.userRepository = userRepository;
        this.personBirthdayRepository = personBirthdayRepository;
    }

    Message handleCallback(ResultDTO result) {
        var chatId = result.getCallbackQuery().getUser().getChatId();
        var month = Integer.parseInt(result.getCallbackQuery().getData());
        var year = Year.now().getValue();

        DatePeriod datePeriod = new DatePeriod(LocalDate.of(year, month, FIRSTDAYOFMONTH),
                LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth()));

        var userId = userRepository.getUserByChatId(chatId).orElseThrow().getId();

        List<PersonBirthday> filteredBirthdays = personBirthdayRepository.findByDatePeriodAndUserId(datePeriod, userId);

        Message msg = new Message();
        msg.setChatId(chatId);
        msg.setText(BirthdayListPrinter.printList(filteredBirthdays));
        return msg;
    }
}

