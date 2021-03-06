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
        } while (!(isCellValid(x, y) && isOpponentNear(x, y)));
        System.out.println("Ход компьютера в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
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
     * <p>
     * </p>
     * Задание №3*
     * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
     * Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
     * <p></p>
     * Проверка условий победы с использованием циклов.
     *
     * @param dot символ игрока
     * @return true, если игрок победил
     */
    public static boolean checkWin(char dot) {
        for (int i = 0; i < SIZE; i++) {

            // проверяем горизонтали
            if (checkWinHV(i, 0, dot, true)) return true;

            // проверяем вертикали
            if (checkWinHV(0, i, dot, false)) return true;

            // проверяем диагонали
            for (int j = 0; j < SIZE; j++) {
                if (checkWinDiagonal(i, j, dot, true)) return true;    // обходим слева направо
                if (checkWinDiagonal(i, j, dot, false)) return true;   // обходим справа налево
            }
        }
        return false;
    }

    /**
     * Проверка победы по вертикале или горизонтале
     *
     * @param i   номер строки в таблице
     * @param j   номер столбца в таблице
     * @param dot символ победы
     * @param hor true для проверки горизонтали; false для проверки вертикали
     * @return true, если победа
     */
    public static boolean checkWinHV(int i, int j, char dot, boolean hor) {
        // убираем лишние проверки, если оставшихся в ряду/столбце фишек меньше, чем DOTS_TO_WIN
        if (hor) {
            if (j > SIZE - DOTS_TO_WIN) return false;
        } else if (i > SIZE - DOTS_TO_WIN) return false;

        // просматриваем горизонталь/вертикаль пока не встретим победную фишку
        while (j < SIZE - DOTS_TO_WIN && map[i][j] != dot) j++;
        while (i < SIZE - DOTS_TO_WIN && map[i][j] != dot) i++;

        int countToWin = 0;                       // счетчик для наступления победы
        while (countToWin < DOTS_TO_WIN && map[i][j] == dot) {
            if (countToWin + 1 == DOTS_TO_WIN) return true;
            else {
                countToWin++;
                if (hor) j++;
                else i++;
            }
        }
        return false;
    }

    /**
     * Проверка победы по диагоналям
     *
     * @param i           номер строки в таблице
     * @param j           номер столбца в таблице
     * @param dot         символ победы
     * @param leftToRight true для обхода слева направо, false для обхода справа налево
     * @return true, если победа
     */
    public static boolean checkWinDiagonal(int i, int j, char dot, boolean leftToRight) {
        // убираем лишние проверки, если оставшихся в диагонале фишек меньше, чем DOTS_TO_WIN
        if (i > SIZE - DOTS_TO_WIN) return false;
        if (leftToRight) {
            if (j > SIZE - DOTS_TO_WIN) return false;
        } else {
            if (j < DOTS_TO_WIN - 1) return false;
        }

        int countToWin = 0;                    // счетчик для наступления победы
        while (countToWin < DOTS_TO_WIN && i < SIZE && j < SIZE && j >= 0 && map[i][j] == dot) {
            if (countToWin + 1 == DOTS_TO_WIN) return true;
            else {
                countToWin++;
                i++;
                if (leftToRight) j++;
                else j--;
            }
        }
        return false;
    }

    /**
     * Задание №4***
     * Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
     * <p>
     * Компьютер делает свой ход рядом с фишкой игрока. В некоторых ситуациях это может усложнить игру.
     *
     * @param x координата ячейки x
     * @param y координата ячейки y
     * @return true, если рядом есть фишка соперника
     */
    public static boolean isOpponentNear(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try {
                    if (map[j][i] == DOT_X) return true;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return false;
    }
}
