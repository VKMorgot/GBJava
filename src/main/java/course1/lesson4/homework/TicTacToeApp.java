package course1.lesson4.homework;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeApp {

    // игровое поле
    public static char[][] map;

    // размер поля
    public static int SIZE;

    // необходимо точек для победы
    public static int DOTS_TO_WIN;

    // описание игровых точек
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    // сканер
    public static Scanner sc = new Scanner(System.in);

    // рандом
    public static Random rnd = new Random();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру \"Крестики-Нолики\"");
        System.out.println("Выберите размер поля: 3х3, 4х4 или 5х5");
        do {
            System.out.println("Введите 3, 4 или 5");
            SIZE = sc.nextInt();
        } while (SIZE != 3 && SIZE != 5 && SIZE != 4);

        switch (SIZE) {
            case 3: {
                DOTS_TO_WIN = 3;
                break;
            }
            case 4:
            case 5: {
                DOTS_TO_WIN = 4;
                break;
            }
        }

        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
//            if (checkWin(DOT_X)) {
            if (checkWin3(DOT_X)) {
                System.out.println("Игрок победил");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
//            if (checkWin(DOT_O)) {
            if (checkWin3(DOT_O)) {
                System.out.println("Компьютер победил");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра окончена");
    }

    /**
     * Инициализация игрового поля
     */
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (char[] mapItem : map) {
            Arrays.fill(mapItem, DOT_EMPTY);
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
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
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
     * Проверка условий победы. <p>
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

    /**
     * Задание №2
     * Переделать проверку победы, чтобы она не была реализована просто набором условий,
     * например, с использованием циклов.
     * <p></p>
     * Задание №3*
     * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
     * Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
     * <p></p>
     * Проверка условий победы с использованием циклов.
     *
     * @param dot символ игрока
     * @return true, если игрок победил
     */
    public static boolean checkWin3(char dot) {
        boolean winD1 = true, winD2 = true;                          // winD - победа по диагоналям
        for (int i = 0; i < SIZE; i++) {

            // проверяем горизонтали
            int h = 0;                                // порядковый номер в таблице, откуда начинаем считать фишки
            while (h < SIZE - DOTS_TO_WIN && map[i][h] != dot) h++;
            int countToWin = 0;                       // счетчик для наступления победы
            while (countToWin < DOTS_TO_WIN && map[i][h] == dot) {
                if (countToWin + 1 == DOTS_TO_WIN) return true;
                else {
                    countToWin++;
                    h++;
                }
            }

            // проверяем вертикали
            int v = 0;                                // порядковый номер в таблице, откуда начинаем считать фишки
            while (v < SIZE - DOTS_TO_WIN && map[v][i] != dot) v++;
            countToWin = 0;                           // счетчик для наступления победы
            while (countToWin < DOTS_TO_WIN && map[v][i] == dot) {
                if (countToWin + 1 == DOTS_TO_WIN) return true;
                else {
                    countToWin++;
                    v++;
                }
            }

            // проверяем диагонали (не работает для поля 5х5)
            if (map[i][i] != dot) winD1 = false;
            if (map[i][SIZE - 1 - i] != dot) winD2 = false;
        }
        return (winD1 || winD2);
    }

}
