package course1.lesson1.homework;

import java.util.Random;

public class HomeWorkApp {

    public static void main(String[] args) {
        printThreeWords();
        System.out.println();

        checkSumSign();
        System.out.println();

        printColor();
        System.out.println();

        compareNumbers();
    }

    public static void printThreeWords() {

        System.out.println("printThreeWords:");

        String[] fruits = {"Orange", "Banana", "Apple"};
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

    }

    public static void checkSumSign() {

        System.out.println("checkSumSign:");

        int a = getRandomNumber(-100, 100);
        int b = getRandomNumber(-100, 100);

        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {

        System.out.println("printColor:");

        int value = getRandomNumber(-100, 200);
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 100) {
            System.out.println("Зеленый");
        } else {
            System.out.println("Желтый");
        }
    }

    public static void compareNumbers() {

        System.out.println("compareNumbers:");

        int a = getRandomNumber(-1000, 1000);
        int b = getRandomNumber(-1000, 1000);

        System.out.println(a);
        System.out.println(b);

        if (a >= b) {
            System.out.println("a>=b");
        } else {
            System.out.println("a<b");
        }
    }

    public static int getRandomNumber (int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
