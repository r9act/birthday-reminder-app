package org.birthdayreminder.configuration;

import lombok.SneakyThrows;

import org.birthdayreminder.dto.in.ResultDTO;
import org.birthdayreminder.handlers.UpdateHandler;
import org.birthdayreminder.service.ReminderTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SchedulerConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerConfiguration.class);

    private final UpdateHandler updateHandler;
    private final ReminderTask reminderTask;

    public SchedulerConfiguration(UpdateHandler updateHandler, ReminderTask reminderTask) {
        this.updateHandler = updateHandler;
        this.reminderTask = reminderTask;
    }

    @SneakyThrows
    @Scheduled(cron = "${telegram.cron.longPollingConfiguration}")
    public void runLongPolling() {
        ResultDTO resultDTOFromUpdate = updateHandler.getResultDTOFromUpdate();
        updateHandler.proceedResultDto(resultDTOFromUpdate);
    }

    @SneakyThrows
    @Scheduled(cron = "${telegram.cron.runReminderConfiguration}")
    public void runReminder() {
        logger.info("Reminder service running!");
        reminderTask.runReminder();
    }
}
