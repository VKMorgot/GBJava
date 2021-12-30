package course1.lesson2.homework;

import java.util.Random;

public class HomeWorkApp {

    /**
     * Выбор случайного целого числа в заданном интервале
     *
     * @param min минимальная граница интервала
     * @param max максимальная граница интервала
     * @return случайное целое число
     */
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * Метод для разделения вывода выполненных пунктов задания
     */
    public static void separator() {
        System.out.println();
        System.out.println();
    }

    interface Printable {
        String print(boolean bool);
    }

    /**
     * Задание №1
     * Проверка, что сумма a + b лежит в пределах от 10 до 20 (включительно)
     *
     * @param a целое число
     * @param b целое число
     * @return true or false
     */
    public static boolean checkSum(int a, int b) {
        return ((a + b) > 10) && ((a + b) <= 20);
    }

    /**
     * Задание №2
     * Печать в консоль, какое число было передано: положительное или отрицательное
     *
     * @param a целое число, ноль считаем положительным числом
     */
    public static void isPositive(int a) {
        if (a >= 0) {
            System.out.printf("Число %d положительное", a);
        } else {
            System.out.printf("Число %d отрицательное", a);
        }
    }

    /**
     * Задание №3
     * Определение знака числа
     *
     * @param a целое число, ноль считаем положительным числом
     * @return true, если число отрицательное; false, если число положительное
     */
    public static boolean isNegative(int a) {
        return (a < 0);
    }

    /**
     * Задание №4
     * Вывод в консоль строки str n-е число раз
     *
     * @param str строка для вывода
     * @param n определяет число выводов строки, не должно быть отрицательным
     */
    public static void printString(String str, int n) {
        if (n >= 0) {
            for (int i = 0; i < n; i++) {
                System.out.println(str);
            }
        }
    }

    public static void main(String[] args) {
        // вызов метода проверки суммы

        //System.out.println(checkSum(getRandomNumber(-5, 20), getRandomNumber(-5, 20)));

        int a = getRandomNumber(-5, 20);
        int b = getRandomNumber(-5, 20);
        String str = "";

        if (!checkSum(a, b)) {
            str = " не";
        }

        System.out.println("Проверка суммы");
        System.out.printf("Сумма чисел %d и %d%s лежит в интервале от 10 до 20", a, b, str);

        // вызов метода определения знака числа, вывод работы метода в консоль
        separator();
        System.out.println("Проверка №1 знака числа");
        isPositive(getRandomNumber(-100, 100));

        // вызов метода определения знака числа, результат работы метода: boolean
        separator();
        int c = getRandomNumber(-100, 100);
        Printable printer = bool -> {
            if (bool)
                return "отрицательное";
            else
                return "положительное";
        };
        System.out.println("Проверка №2 знака числа");
        System.out.printf("Число %d %s", c, printer.print(isNegative(c)));

        // второй вызов метода определения знака числа, результат работы метода: boolean
        System.out.println();
        c = getRandomNumber(-100, 100);
        if (isNegative(c)) {
            str = "отрицательное";
        } else {
            str = "положительное";
        }
        System.out.printf("Число %d %s", c, str);

        // вывод строки в консоль
        separator();
        int n = getRandomNumber(0, 10);
        printString("Выводим строку " + n + " раз", n);
    }
}
