package course1.lesson5.homework;

import java.util.Random;

public class HomeworkApp {

    // массив имен
    static final String[] NAMES = {"Иван", "Петр", "Сергей", "Антон", "Тихон", "Спиридон", "Афанасий", "Георгий"};
    // массив фамилий
    static final String[] SURNAMES = {"Иванов", "Петров", "Сидоров", "Жданов", "Лебедев", "Кулыванов", "Трапорян"};
    // массив должностей
    static final String[] POSITIONS = {"Разработчик", "Ведущий разработчик", "Менеджер проекта", "Тестировщик",
            "Ведущий тестировщик", "Руководитель тестирования", "Руководитель разработки", "Аналитик",
            "Программный архитектор", "Технический писатель"};
    // массив электронных почт
    static final String[] MAILS = {"destroyer87", "fireman", "tictoker", "manOfHoner", "BLM", "tor8", "krapiva", "veslo"};
    // массив доменов
    static final String[] DOMAINS = {"@mail.ru", "@gmail.com", "@yandex.ru", "@rambler.ru", "@bk.ru", "@yahoo.com"};
    // массив кодов номеров телефонов
    static final String[] PHONES = {"923", "919", "910", "962", "982", "903", "965", "905", "925"};
    // рандом
    static final Random rnd = new Random();

    /**
     * Возвращает полное имя сотрудника
     * @param names массив имен
     * @param surnames массив фамилий
     * @return полное имя
     */
    static String getFullName(String[] names, String[] surnames) {
        return surnames[rnd.nextInt(surnames.length)] + " " + names[rnd.nextInt(names.length)];
    }

    /**
     * Возвращает должность сотрудника
     * @param positions массив должностей
     * @return должность
     */
    static String getPosition(String[] positions) {
        return positions[rnd.nextInt(positions.length)];
    }

    /**
     * Возвращает почту сотрудника
     * @param mails массив имен почт
     * @param domains массив доменов
     * @return почта
     */
    static String getEmail(String[] mails, String[] domains) {
        return mails[rnd.nextInt(mails.length)] + domains[rnd.nextInt(domains.length)];
    }

    /**
     * Возвращает номер телефона сотрудника в формате: +79239581232
     * @param phones массив номеров
     * @return номер телефона
     */
    static String getPhoneNumber(String[] phones){
        return "+7" + phones[rnd.nextInt(phones.length)] + (int)(rnd.nextFloat() * 10000000);
    }

    /**
     * Возвращает зарплату сотрудника
     * @return зарплата в диапазоне от 50 000 до 100 000
     */
    static int getSalary() {
        return (50 + rnd.nextInt(51)) * 1000;
    }

    /**
     * Возвращает возраст сотрудника
     * @return возраст в диапазоне от 20 до 60
     */
    static int getAge() {
        return 20 + rnd.nextInt(41);
    }

    /**
     * Выводит информацию о сотрудниках старше указанного возраста
     * @param employees массив сотрудников
     * @param age контрольный возраст
     */
    static void printEmployees(Employee[] employees, int age) {
        for (Employee employee : employees) {
            if (employee.getAge() > age) {
                employee.info();
                System.out.println();
            }
        }
    }

    /**
     * Создает запись о сотруднике
     * Метод сделан на случай, если потребуется еще один метод с другим способом заведения записи, например, ручной ввод
     * @param fullName полное имя
     * @param position должность
     * @param email почтовый адрес
     * @param phone телефонный номер
     * @param salary зарплата
     * @param age возраст
     * @return запись о сотруднике
     */
    static Employee recordEmployee(String fullName, String position, String email, String phone, int salary, int age) {
        return new Employee(fullName, position, email, phone, salary, age);
    }

    public static void main(String[] args) {

        // Создаем массив из 5 сотрудников
        Employee[] employees = new Employee[5];

        // Заполняем массив случайными значениями
        for (int i = 0; i < employees.length; i++) {
            employees[i] = recordEmployee(
                    getFullName(NAMES, SURNAMES),
                    getPosition(POSITIONS),
                    getEmail(MAILS, DOMAINS),
                    getPhoneNumber(PHONES),
                    getSalary(),
                    getAge());
        }

        // Выводим информацию о сотрудниках старше 40 лет
        printEmployees(employees, 40);
    }
}
