package org.birthdayreminder.domain.service.impl;

import org.birthdayreminder.domain.model.PersonBirthday;

import java.util.List;

public class BirthdayListPrinter {

    /**
     * @param list ДР
     * @return читаемый вывод списка ДР
     */
    public static String printList(List<PersonBirthday> list) {
        StringBuilder lineToSend = new StringBuilder();
        for (PersonBirthday pb : list) {
            var name = pb.name();
            var surname = "";
            var dateDay = pb.date().getDayOfMonth();
            var dateMonth = pb.date().getMonth();
            if (pb.surname() == null) {
                lineToSend.append(name).append(": ").append(dateDay).append(" ").append(dateMonth).append("\n");
            } else {
                surname = pb.surname();
                lineToSend.append(name).append(" ").append(surname).append(": ").append(dateDay).append(" ").append(dateMonth).append("\n");
            }
        }
        return lineToSend.toString();
    }
}
