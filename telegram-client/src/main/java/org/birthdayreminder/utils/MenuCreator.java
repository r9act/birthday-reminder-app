package org.birthdayreminder.utils;

import org.birthdayreminder.dto.out.InlineKeyboardButton;
import org.birthdayreminder.dto.out.InlineKeyboardMarkup;
import org.birthdayreminder.dto.out.KeyboardButton;
import org.birthdayreminder.dto.out.ReplyKeyboardMarkup;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuCreator {

    public static ReplyKeyboardMarkup getMainMenuKeyboard() {

        List<KeyboardButton> row1 = new ArrayList<>();
        List<KeyboardButton> row2 = new ArrayList<>();
        List<KeyboardButton> row3 = new ArrayList<>();

        row1.add(new KeyboardButton("Show all birthdays"));
        row2.add(new KeyboardButton("Select month to show birthdays"));
        row3.add((new KeyboardButton("On/off reminder")));

        List<List<KeyboardButton>> rowList = List.of(row1, row2, row3);

        return new ReplyKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup getInlineKeyboardMonth() {

        List<InlineKeyboardButton> listOfMonths = Arrays.stream(Month.values()).map(m -> new InlineKeyboardButton(m.name(), String.valueOf(m.getValue())))
                 .toList();

        System.out.println(listOfMonths.get(0).getText());

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(listOfMonths.get(11));
        row1.add(listOfMonths.get(0));
        row1.add(listOfMonths.get(1));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(listOfMonths.get(2));
        row2.add(listOfMonths.get(3));
        row2.add(listOfMonths.get(4));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(listOfMonths.get(5));
        row3.add(listOfMonths.get(6));
        row3.add(listOfMonths.get(7));

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(listOfMonths.get(8));
        row4.add(listOfMonths.get(9));
        row4.add(listOfMonths.get(10));

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        rowList.add(row4);

        return new InlineKeyboardMarkup(rowList);
    }
}
