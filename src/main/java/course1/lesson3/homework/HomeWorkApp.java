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
     * С помощью цикла и условия заменить 0 на 1, 1 на 0.
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

    /**
     * Задание №2
     * Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100.
     */
    public static void from1To100() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Задание №3
     * Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2.
     */
    public static void multiply6() {

        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };

        // Массив до изменений
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }

        // Массив после изменений
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Задание №4
     * Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
     * и с помощью цикла(-ов) заполнить его диагональные элементы единицами
     * (можно только одну из диагоналей, если обе сложно).
     * Определить элементы одной из диагоналей можно по следующему принципу:
     * индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n].
     *
     * @param arr квадратный двумерный целочисленный массив
     */
    public static void squareArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if ((i == j) || (j == arr[i].length - 1 - i)) arr[i][j] = 1;
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Задание №5
     * Написать метод, принимающий на вход два аргумента: len и initialValue,
     * и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue.
     *
     * @param len длина массива
     * @param initialValue значение каждой ячейки массива
     * @return получившийся массив
     */
    public static int[] arrayLen(int len, int initialValue) {
        int[] arr = new int[len];
        Arrays.fill(arr, initialValue);
        return arr;
    }

    public static void main(String[] args) {

        System.out.println("Задание №1");
        int[] array01 = new int[10];
        changeZeroAndOne(array01);
        System.out.println();

        System.out.println("Задание №2");
        from1To100();
        System.out.println();

        System.out.println("Задание №3");
        multiply6();
        System.out.println();

        System.out.println("Задание №4");
        int n = 10;
        int[][] square = new int[n][n];
        squareArray(square);
        System.out.println();

        System.out.println("Задание №5");
        System.out.println(Arrays.toString(arrayLen(10, 42)));
        System.out.println();
    }
}
