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
            var name = pb.name().equals("TODAY") ? "*TODAY*" : escapeMarkdownV2(pb.name());
            var surname = "";
            var dateDay = escapeMarkdownV2(String.valueOf(pb.date().getDayOfMonth()));
            var dateMonth = escapeMarkdownV2(String.valueOf(pb.date().getMonth()));
            if (pb.surname() == null) {
                lineToSend.append(name).append(": ").append(dateDay).append(" ").append(dateMonth).append("\n");
            } else {
                surname = escapeMarkdownV2(pb.surname());
                lineToSend.append(name).append(" ").append(surname).append(": ").append(dateDay).append(" ").append(dateMonth).append("\n");
            }
        }
        return lineToSend.toString();
    }

    public static String escapeMarkdownV2(String text) {
        String[] specialCharacters = {"_", "*", "[", "]", "(", ")", "~", "`", ">", "#", "+", "-", "=", "|", "{", "}", ".", "!"};
        String escapedText = text;
        for (String ch : specialCharacters) {
            escapedText = escapedText.replace(ch, "\\" + ch);
        }
        return escapedText;
    }
}
