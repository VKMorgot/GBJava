package course1.lesson4.homework;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeApp {

    public static int SIZE = 3;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rnd = new Random();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру \"Крестики-Нолики 3х3\"");
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Игрок победил");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Компьютер победил");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    /**
     * Инициализация игрового поля
     */
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать игрового поля
     */
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Проверяем возможность установки фишки в ячейку
     *
     * @param x координата ячейкий X
     * @param y координата ячейки Y
     * @return true, если можно установить фишку в заданную ячейку
     */
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE && y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }

    /**
     * Ход человека
     */
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    /**
     * Ход компьютера
     */
    public static void aiTurn() {
        int x, y;
        do {
            x = rnd.nextInt(SIZE);
            y = rnd.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Ход компьютера в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    /**
     * Проверка условий победы.
     * Перебор всех возможных условий для победы на поле 3х3
     *
     * @param dot символ игрока
     * @return true, если игрок победил
     */
    public static boolean checkWin(char dot) {
        return (map[0][0] == dot && map[0][1] == dot && map[0][2] == dot) ||
                (map[1][0] == dot && map[1][1] == dot && map[1][2] == dot) ||
                (map[2][0] == dot && map[2][1] == dot && map[2][2] == dot) ||
                (map[0][0] == dot && map[1][0] == dot && map[2][0] == dot) ||
                (map[0][1] == dot && map[1][1] == dot && map[2][1] == dot) ||
                (map[0][2] == dot && map[1][2] == dot && map[2][2] == dot) ||
                (map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) ||
                (map[0][2] == dot && map[1][1] == dot && map[2][0] == dot);
    }

    /**
     * Задание №2
     * Переделать проверку победы, чтобы она не была реализована просто набором условий,
     * например, с использованием циклов.
     *
     * Проверка условий победы с использованием циклов на поле 3х3
     * @param dot символ игрока
     * @return true, если игрок победил
     */
    public static boolean checkWin2(char dot) {
        boolean winH, winV;            // winH - победа по горизонтале, winV - победа по вертикале
        winH = winV = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] != dot) {
                    winH = false;
                    break;
                }
            }
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] != dot) {
                    winV = false;
                    break;
                }
            }
        }
        return winH || winV;
    }

    /**
     * Проверяем, пустое игровое поле или нет
     *
     * @return true, если поле пустое и нет возможности для хода
     */
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
}
