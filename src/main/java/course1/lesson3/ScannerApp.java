package course1.lesson3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ScannerApp {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
/*
        int a = sc.nextInt();
        String b = sc.nextLine();
        String c = sc.next();
        boolean f = sc.nextBoolean();
        sc.close();

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(f);
*/
        getNumberFromScanner("Введите число от 5 до 10", 5, 10);

        int[][] arr = new int[2][3];
        for (int[] ints : arr) {
            Arrays.fill(ints, 8);
        }

        print2DArray(arr);
        System.out.println();

        Random rnd = new Random();
        int x = rnd.nextInt(10);
        while (x != 9) {
            System.out.print(x + " ");
            x = rnd.nextInt(10);
        }
        System.out.println(x);

    }

    public static int getNumberFromScanner(String message, int min, int max) {
        int x;
        do {
            System.out.println(message);
            x = sc.nextInt();
        } while (x < min || x > max);
        sc.close();
        return x;
    }

    public static void print2DArray(int[][] arr) {
        for (int i = 0; i <= arr[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(Arrays.deepToString(arr));
    }
}
