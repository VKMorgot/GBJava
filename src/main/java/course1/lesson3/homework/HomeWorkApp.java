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
     * Класс для вывода результатов по нахождению минимума и максимума массива (Задание №6)
     */
    public static class MinMaxResult {
        private final int minElement;
        private final int maxElement;

        public MinMaxResult(int min, int max) {
            this.minElement = min;
            this.maxElement = max;
        }
    }

    /**
     * Создание массива заданной длины и вывод его в консоль
     *
     * @param len размер массива
     * @param min минимальное значение элемента массива
     * @param max максимальное значение элемента массива
     * @return массив
     */
    public static int[] createArray(int len, int min, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = getRandomNumber(min, max);
        }
        System.out.println("Создан массив");
        System.out.println(Arrays.toString(arr));
        return arr;
    }

    /**
     * Подсчет суммы элементов массива на заданном интервале
     *
     * @param arr    массив для подсчета суммы
     * @param start  номер первого элемента интервала
     * @param finish номер последнего эелемента интервала
     * @return сумма элементов интервала
     */
    public static int calculateSum(int[] arr, int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            sum += arr[i];
        }
        return sum;
    }

    /**
     * Сдвиг элементов массива на 1 вправо
     *
     * @param arr массив для сдвига
     */
    public static void shiftArrayToOne(int[] arr) {
        int item = arr[arr.length - 1];

        // копирование массива встроенной функцией
        System.arraycopy(arr, 0, arr, 1, arr.length - 1);

            /* ручное копирование массива
            for (int i = arr.length - 1; i > 0; i--) {
                arr[i] = arr[i - 1];
            } */

        arr[0] = item;
    }

    /**
     * Задание №1
     * Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0.
     */
    public static void changeZeroAndOne() {

        // задаем массив из 0 и 1 размером 10
        int[] arr = createArray(10, 0, 2);

        // меняем 0 на 1, 1 на 0
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                arr[i] = 0;
            } else {
                arr[i] = 1;
            }
        }

        // выводим измененный массив в консоль
        System.out.println("Измененный массив");
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

        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

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
     * @param len          длина массива
     * @param initialValue значение каждой ячейки массива
     * @return получившийся массив
     */
    public static int[] arrayLen(int len, int initialValue) {
        int[] arr = new int[len];
        Arrays.fill(arr, initialValue);
        return arr;
    }

    /**
     * Задание №6*. Первый способ. Поиск через сортировку
     * Задать одномерный массив и найти в нем минимальный и максимальный элементы
     *
     * @param arr одномерный массив
     * @return минимальное и максимальное значения массива
     */
    public static MinMaxResult arrayMinMax(int[] arr) {
        Arrays.sort(arr);
        System.out.println("Массив после сортировки:");
        System.out.println(Arrays.toString(arr));
        int min = arr[0];
        int max = arr[arr.length - 1];
        return new MinMaxResult(min, max);
    }

    /**
     * Задание №6*. Второй способ. Поиск через обход массива
     * Задать одномерный массив и найти в нем минимальный и максимальный элементы
     *
     * @param arr одномерный массив
     * @return минимальное и максимальное значения массива
     */
    public static MinMaxResult arrayMinMax2(int[] arr) {

        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) min = arr[i];
            if (max < arr[i]) max = arr[i];
        }

        return new MinMaxResult(min, max);
    }

    /**
     * Задание №7**
     * Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
     * если в массиве есть место, в котором сумма левой и правой части массива равны.
     * **Примеры:
     * checkBalance([2, 2, 2, 1, 2, 2, ||| 10, 1]) → true, т.е. 2 + 2 + 2 + 1 + 2 + 2 = 10 + 1
     * checkBalance([1, 1, 1, ||| 2, 1]) → true, т.е. 1 + 1 + 1 = 2 + 1
     * граница показана символами |||, эти символы в массив не входят и не имеют никакого отношения к ИЛИ.
     *
     * @param arr не пустой одномерный целочисленный массив
     * @return результат проверки
     */
    public static boolean checkBalance(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (calculateSum(arr, 0, i) == calculateSum(arr, i + 1, arr.length - 1)) {
                // для удобства восприятия нумерацию элементов массива начнем с 1
                System.out.println("Порядковый номер элемента, где левая и правая части массива равны: " + (i + 1));
                return true;
            }
        }
        System.out.println("В массиве нет места, в котором сумма левой и правой частей массива равны");
        return false;
    }

    /**
     * Задание №8***
     * Написать метод, которому на вход подается одномерный массив и число n
     * (может быть положительным, или отрицательным), при этом метод должен сместить все элементы массива на n позиций.
     * Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
     * Примеры:
     * [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
     * [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
     * При каком n в какую сторону сдвиг можете выбирать сами.
     *
     * @param arr массив для сдвига
     * @param n   шаг сдвига (положительный - сдвиг вправо, отрицательный - сдвиг влево)
     */
    public static void shiftArray(int[] arr, int n) {
        int shift = n % arr.length;                             // если сдвиг больше длины массива

        if (shift < 0) {
            shift = arr.length + shift;                         // меняем сдвиг влево на сдвиг вправо
        }

        if (shift != 0) {                                       // если shift == 0, то сдвиг не нужен
            for (int i = 0; i < shift; i++) {
                shiftArrayToOne(arr);                           // сдвигаем вправо на 1
            }
        }

        System.out.println("Массив после сдвига на " + n);
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {

        System.out.println("Задание №1");
        changeZeroAndOne();
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

        System.out.println("Задание №6*. Первый способ");
        MinMaxResult result = arrayMinMax(createArray(10, -100, 100));
        System.out.println("Минимальное значение массива: " + result.minElement);
        System.out.println("Максимальное значение массива: " + result.maxElement);
        System.out.println();

        System.out.println("Задание №6*. Второй способ");
        MinMaxResult result2 = arrayMinMax2(createArray(10, -100, 100));
        System.out.println("Минимальное значение массива: " + result2.minElement);
        System.out.println("Максимальное значение массива: " + result2.maxElement);
        System.out.println();

        System.out.println("Задание №7**");
        int[][] arrays = new int[3][];
        arrays[0] = createArray(10, 0, 3);
        arrays[1] = new int[]{2, 2, 2, 1, 2, 2, 10, 1};
        arrays[2] = new int[]{1, 1, 1, 2, 1};
//        System.out.println(Arrays.deepToString(arrays));

        for (int[] array : arrays) {
            System.out.println();
            System.out.println("Массив для проверки");
            System.out.println(Arrays.toString(array));
            checkBalance(array);
        }
        System.out.println();

        System.out.println("Задание №8***");
        shiftArray(createArray(10, 0, 10), getRandomNumber(-20, 20));
    }
}