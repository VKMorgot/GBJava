package course2.lesson5.homework;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreadsApp {

    // размерность массива
    private static final int SIZE = 10_000_007;
    // чем изначально заполнять массив
    private static final float VALUE = 1f;
    // ручное задание потоков
    private static final int NUM_THREADS = 4;

    /**
     * Создаем одномерный массив
     *
     * @param size  размер массива
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
     * @return Новое значение.
     * Отличается от значения, которое можно получить в калькуляторе из-за преобразования во float
     */
    private static float calcNewValue(float value, int num) {
        return (float) (value * Math.sin(0.2D + num / 5D) * Math.cos(0.2D + num / 5D) * Math.cos(0.4D + num / 2D));
    }

    /**
     * Изменяем значения в массиве по заданной в calcNewValue формуле
     *
     * @param arr           массив данных
     * @param startPosition Стартовая позиция, для которой начинаем считать значения.
     *                      Переменная нужна для подсчета значений в разделенных массивах
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
     * Изменяем массив по заданной формуле С использованием заданного числа потоков:
     * разбиваем массив на массивы по числу потоков,
     * в потоках высчитываем новые значения,
     * потом склеиваем эти массивы
     *
     * @param arr        массив данных
     * @param numThreads число потоков
     * @return время изменения значений в массиве, включая создание потоков и вспомогательных массивов
     */
    private static long modifyArrayCustomThreads(float[] arr, int numThreads) {

        // Ограничиваем число потоков размером массива.
        // Если не ограничить, в случае превышения, будет считаться в одном потоке
        if (numThreads > arr.length) numThreads = arr.length;

        long startTime = System.currentTimeMillis();

        // создаем вспомогательные массивы
        ArrayList<float[]> arrays = new ArrayList<>();
        for (int i = 0; i < numThreads - 1; i++) {
            arrays.add(new float[arr.length / numThreads]);
        }
        arrays.add(new float[arr.length / numThreads + arr.length % numThreads]); // если размер массива не кратен числу потоков

        // создаем потоки для расчетов
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            int finalI = i;
            if (i == 0) threads.add(new Thread(() ->
                    changeArrValues(arrays.get(finalI), 0)));
            else threads.add(new Thread(() ->
                    changeArrValues(arrays.get(finalI), arrays.get(finalI - 1).length * finalI)));
        }

        // разбивка массива для расчетов
        for (int i = 0; i < arrays.size(); i++) {
            if (i == 0) System.arraycopy(arr, 0, arrays.get(i), 0, arrays.get(i).length);
            else System.arraycopy(arr, arrays.get(i - 1).length * i, arrays.get(i), 0, arrays.get(i).length);
        }

        // просчет каждого массива
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // склейка массивов в один
        for (int i = 0; i < arrays.size(); i++) {
            if (i == 0) System.arraycopy(arrays.get(i), 0, arr, 0, arrays.get(i).length);
            else System.arraycopy(arrays.get(i), 0, arr, arrays.get(i - 1).length * i, arrays.get(i).length);
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Изменяем массив по заданной формуле С использованием 2 потоков:
     * разбиваем массив на два массива, в двух потоках высчитываем новые значения, потом склеиваем эти массивы
     *
     * @param arr массив данных
     * @return время изменения значений в массиве
     */
    private static long modifyArrayThreads(float[] arr) {

        long startTime = System.currentTimeMillis();

        // создаем вспомогательные массивы
        float[] arr1 = new float[arr.length / 2];
        float[] arr2 = new float[arr.length - arr.length / 2];    // на случай, если исходный массив будет нечетной длины

        // создаем потоки для расчетов
        Thread thread1 = new Thread(() -> changeArrValues(arr1, 0));
        Thread thread2 = new Thread(() -> changeArrValues(arr2, arr1.length));

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
     * @param msg  текст сообщения
     */
    private static void checkArrays(float[] arr1, float[] arr2, String msg) {
        System.out.print(msg);
        if (Arrays.equals(arr1, arr2)) {
            System.out.println(": массивы одинаковы");
        } else {
            System.out.println(": массивы не одинаковы");
        }
    }

    public static void main(String[] args) {
        // создаем массивы для изменения значений
        float[] arrNoThreads = createArr(SIZE, VALUE);
        float[] arrThreads = createArr(SIZE, VALUE);
        float[] arrCustomThreads = createArr(SIZE, VALUE);
        // проверяем, что значения в массивах одинаковы
        checkArrays(arrNoThreads, arrThreads, "Сравнение массивов arrNoThreads и arrThreads после создания");
        checkArrays(arrNoThreads, arrCustomThreads, "Сравнение массивов arrNoThreads и arrCustomThreads после создания");
        // меняем массив без потоков и запоминаем, сколько времени выполнялись изменения
        long timeModifyArray = modifyArray(arrNoThreads);
        // проверяем, что массивы перестали быть одинаковыми
        checkArrays(arrNoThreads, arrThreads, "Сравнение массивов arrNoThreads и arrThreads после изменения массива arrNoThreads");
        // меняем массив с потоками и запоминаем, сколько времени выполнялись изменения
        long timeModifyArrayThreads = modifyArrayThreads(arrThreads);
        // меняем массив с заданным числом потоков и запоминаем, сколько времени выполнялись изменения
        long timeModifyArrayCustomThreads = modifyArrayCustomThreads(arrCustomThreads, NUM_THREADS);
        // проверяем, что массивы снова одинаковы
        checkArrays(arrNoThreads, arrThreads, "Сравнение массивов arrNoThreads и arrThreads после изменения массива arrThreads");
        checkArrays(arrNoThreads, arrCustomThreads, "Сравнение массивов arrNoThreads и arrCustomThreads после изменения массива arrCustomThreads");
        // выводим время работы методов
        System.out.println("Время работы метода в основном потоке: " + timeModifyArray);
        System.out.println("Время работы метода с 2 потоками: " + timeModifyArrayThreads);
        System.out.println("Время работы метода с заданными " + NUM_THREADS + " потоками: " + timeModifyArrayCustomThreads);
    }
}
