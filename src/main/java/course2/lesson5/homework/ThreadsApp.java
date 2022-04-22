package course2.lesson5.homework;

import java.util.Arrays;

public class ThreadsApp {

    // размерность массива
    private static final int SIZE = 10_000_007;
    // чем изначально заполнять массив
    private static final float VALUE = 1f;

    /**
     * Создаем одномерный массив
     *
     * @param size  размер массив
     * @param value чем заполняем массив
     * @return заполненный массив
     */
    private static float[] createArr(int size, float value) {
        float[] arr = new float[size];
        Arrays.fill(arr, value);
        return arr;
    }

    /**
     * Считаем новое значение в ячейке массива по заданной формуле.
     *
     * @param value значение в ячейке
     * @param num   номер ячейки
     * @return новое значение.
     * Отличается от значения, которое можно получить в калькуляторе из-за преобразования во float
     */
    private static float calcNewValue(float value, int num) {
        return (float) (value * Math.sin(0.2D + num / 5D) * Math.cos(0.2D + num / 5D) * Math.cos(0.4D + num / 2D));
    }

    /**
     * Изменяем значения в массиве по заданной в calcNewValue формуле
     *
     * @param arr           массив данных
     * @param startPosition стартовая позиция, для которой начинаем считать значения.
     *                      переменная нужна для подсчета значений в разделенных массивах
     */
    private static void changeArrValues(float[] arr, int startPosition) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = calcNewValue(arr[i], startPosition + i);
        }
    }

    /**
     * Изменяем массив по заданной формуле БЕЗ использования потоков
     *
     * @param arr массив данных
     * @return время изменения значений в массиве
     */
    private static long modifyArray(float[] arr) {
        long startTime = System.currentTimeMillis();
        changeArrValues(arr, 0);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Изменяем массив по заданной формуле С использованием потоков:
     * разбиваем массив на два массива, в двух потоках высчитываем новые значения, потом скливаем эти массивы
     *
     * @param arr массив данных
     * @return время изменения значений в массиве
     */
    private static long modifyArrayThreads(float[] arr) {

        // создаем вспомогательные массивы
        float[] arr1 = new float[arr.length / 2];
        float[] arr2 = new float[arr.length - arr.length / 2];    // на случай, если исходный массив будет нечетной длины

        // создаем потоки для рассчетов
        Thread thread1 = new Thread(() -> changeArrValues(arr1, 0));
        Thread thread2 = new Thread(() -> changeArrValues(arr2, arr1.length));

        long startTime = System.currentTimeMillis();

        // разбивка массива на два
        System.arraycopy(arr, 0, arr1, 0, arr1.length);
        System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);

        // просчет каждого из двух массивов
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // склейка массивов в один
        System.arraycopy(arr1, 0, arr, 0, arr1.length);
        System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Выводим информацию о равенстве массивов
     *
     * @param arr1 первый массив
     * @param arr2 второй массив
     */
    private static void checkArrays(float[] arr1, float[] arr2) {
        if (Arrays.equals(arr1, arr2)) {
            System.out.println("Массивы после вычисления одинаковы");
        } else {
            System.out.println("Массивы после вычисления не одинаковы");
        }
    }

    public static void main(String[] args) {
        // создаем массивы для изменения значений
        float[] arr = createArr(SIZE, VALUE);
        float[] arrThreads = createArr(SIZE, VALUE);
        // проверяем, что значения в массивах одинаковы
        checkArrays(arr, arrThreads);
        // меняем массив без потоков и запоминаем, сколько времени выполнялись изменения
        long timeModifyArray = modifyArray(arr);
        // проверяем, что массивы перестали быть одинаковыми
        checkArrays(arr, arrThreads);
        // меняем массив с потоками и запоминаем, сколько времени выполнялись изменения
        long timeModifyArrayThreads = modifyArrayThreads(arrThreads);
        // проверяем, что массивы снова одинаковы
        checkArrays(arr, arrThreads);
        // выводим время работы первого и второго методов
        System.out.println("Время работы метода без потоков: " + timeModifyArray);
        System.out.println("Время работы метода с потоками: " + timeModifyArrayThreads);
    }
}
