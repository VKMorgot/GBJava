package course2.lesson3.homework.task2;

import java.util.Random;

/**
 * t2. Написать простой класс «Телефонный Справочник», который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи,
 * а с помощью метода get() искать номер телефона по фамилии.
 * Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 * тогда при запросе такой фамилии должны выводиться все телефоны.
 * <p>
 * <b>в качестве ключа для Map использовать фамилию человека</b>
 * <p>
 * <i>Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
 * взаимодействие с пользователем через консоль и т.д).
 * Консоль использовать только для вывода результатов проверки телефонного справочника.</i>
 */

public class PhoneBookApp {

    // массив кодов номеров телефонов
    private static final String[] PHONES = {"923", "919", "910", "962", "982", "903", "965", "905", "925"};

    // массив фамилий
    private static final String[] SURNAMES = {"Иванов", "Петров", "Сидоров", "Жданов", "Лебедев"};

    private static final Random rnd = new Random();


    /**
     * Возвращает номер телефона сотрудника в формате: +79239581232
     * @param phones массив номеров
     * @return номер телефона
     */
    private static String getPhoneNumber(String[] phones){
        return "+7" + phones[rnd.nextInt(phones.length)] + (int)(rnd.nextFloat() * 10000000);
    }

    public static void main(String[] args) {
        // создаем телефонный справочник с повторяющимися фамилиями
//        PhoneBookInt phoneBook = new PhoneBookKeyIsPhone();
        PhoneBookInt phoneBook = new PhoneBookKeyIsLastName();
        for (int i = 0; i < SURNAMES.length * 2; i++) {
            phoneBook.add(SURNAMES[rnd.nextInt(SURNAMES.length)], getPhoneNumber(PHONES));
        }

        // читаем справочник, проверяем все фамилии из массива фамилий
        // выводим фамилию и список номеров телефонов, если они есть
        // если номеров нет, выводим сообщение, что номер не найден
        System.out.println("Список телефонов:");
        for (String lastName : SURNAMES) {
            System.out.print(lastName + ": " + phoneBook.get(lastName) + "\n");
        }
    }
}
