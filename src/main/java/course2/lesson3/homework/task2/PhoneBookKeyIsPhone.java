package course2.lesson3.homework.task2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Телефонный справочник с ключом по номеру телефона
 */
public class PhoneBookKeyIsPhone implements PhoneBookInt {

    // телефонный справочник
    private final HashMap<String, String> phoneBook = new HashMap<>();

    /**
     * Добавляем запись в телефонный справочник
     * @param lastName фамилия
     * @param phoneNumber телефон
     */
    public void add(String lastName, String phoneNumber) {
        phoneBook.put(phoneNumber, lastName);
    }

    /**
     * Ищем номер телефона по фамилии
     * @param lastName фамилия
     * @return список номеров телефона, относящихся к фамилии
     */
    public ArrayList<String> get(String lastName) {
        ArrayList<String> numList = new ArrayList<>();

        phoneBook.forEach((key, value) -> {
            if (value.equals(lastName)) numList.add(key);
        });

        if (numList.size() == 0) numList.add("номер телефона не найден");
        return numList;
    }
}
