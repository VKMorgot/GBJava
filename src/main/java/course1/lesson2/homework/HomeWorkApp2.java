package course1.lesson2.homework;

import java.util.Random;

public class HomeWorkApp2 {

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

        separator();
        System.out.println("Проверка числа");
        isPositive(getRandomNumber(-100, 100));


    }

    /**
     * Задание №2
     * Печать в консоль, какое число было передано: положительное или отрицательное
     * @param a целое число, ноль считаем положительным числом
     */
    public static void isPositive (int a) {
        if (a >= 0) {
            System.out.printf("Число %d положительное", a);
        } else {
            System.out.printf("Число %d отрицательное", a);
        }
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
}
