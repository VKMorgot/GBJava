package course2.lesson3.homework.task2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Телефонный справочник с ключом по фамилии
 */
public class PhoneBookKeyIsLastName implements PhoneBookInt {

    // телефонный справочник
    private final HashMap<String, HashSet<String>> phoneBook = new HashMap<>();

//    /**
//     * Добавляем запись в телефонный справочник
//     * без использования merge
//     * @param lastName фамилия
//     * @param phoneNumber телефон
//     */
//    public void add(String lastName, String phoneNumber) {
//        if (phoneBook.containsKey(lastName)) {
//            phoneBook.get(lastName).add(phoneNumber);
//        } else {
//            HashSet<String> newNumber = new HashSet<>();
//            newNumber.add(phoneNumber);
//            phoneBook.put(lastName, newNumber);
//        }
//    }

    /**
     * Добавляем запись в телефонный справочник
     * с использованием merge
     * @param lastName фамилия
     * @param phoneNumber телефон
     */
    public void add(String lastName, String phoneNumber) {
        HashSet<String> newNumber = new HashSet<>();
        newNumber.add(phoneNumber);
        phoneBook.merge(lastName, newNumber,
                (exist, newPhone) -> (HashSet<String>) Stream.concat(exist.stream(), newPhone.stream()).collect(Collectors.toSet()));
    }

    /**
     * Ищем номер телефона по фамилии
     *
     * @param lastName фамилия
     * @return список номеров телефона, относящихся к фамилии
     */
    public ArrayList<String> get(String lastName) {
        HashSet<String> notPresented = new HashSet<>();
        notPresented.add("номер телефона не найден");
        return new ArrayList<>(phoneBook.getOrDefault(lastName, notPresented));
    }
}
