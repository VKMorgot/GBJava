package course1.lesson3.homework;

import java.util.Arrays;
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
     * Задание №1
     * Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0;
     *
     * @param arr массив из 0 и 1
     */
    public static void changeZeroAndOne(int[] arr) {

        // задаем массив из 0 и 1
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getRandomNumber(0, 2);
        }

        // выводим первоначальный массив в консоль
        System.out.println(Arrays.toString(arr));

        // меняем 0 на 1, 1 на 0
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                arr[i] = 0;
            } else {
                arr[i] = 1;
            }
        }

        // выводим измененный массив в консоль
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {

        System.out.println("Задание №1");
        int[] array01 = new int[10];
        changeZeroAndOne(array01);

    }
}
