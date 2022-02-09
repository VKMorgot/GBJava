package course1.lesson8.homework.TicTacToeNew;

import java.util.Arrays;
import java.util.Random;

class TicTacToe {

    TicTacToe(int size, int dotsToWin) {
        this.SIZE = size;
        this.DOTS_TO_WIN = dotsToWin;
        initMap();
    }

    // игровое поле
    private char[][] map;

    // размер поля
    private final int SIZE;

    // необходимо точек для победы
    private final int DOTS_TO_WIN;

    // описание игровых точек
    final char DOT_EMPTY = '•';
    final char DOT_X = 'X';
    final char DOT_O = 'O';

    // заголовок
    static final String TITLE = "Крестики-нолики";

    /**
     * Инициализация игрового поля
     */
    private void initMap() {
        map = new char[SIZE][SIZE];
        for (char[] mapItem : map) {
            Arrays.fill(mapItem, DOT_EMPTY);
        }
    }

    /**
     * Печать игрового поля
     */
    void printMap() {
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
    private boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }

    /**
     * Ход человека через интерфейс
     */
    boolean humanTurnGUI(int x, int y) {
        if (isCellValid(y, x)) {
            map[x][y] = DOT_X;
            System.out.println("Игрок походил в точку " + (y + 1) + " " + (x + 1));
            return true;
        }
        return false;
    }

    /**
     * Ход компьютера
     */
    int[] aiTurn() {
        int x, y;
        Random rnd = new Random();
        do {
            x = rnd.nextInt(SIZE);
            y = rnd.nextInt(SIZE);
        } while (!(isCellValid(x, y) && isOpponentNear(x, y)));
        System.out.println("Ход компьютера в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
        return new int[]{x, y};
    }

    /**
     * Проверяем, пустое игровое поле или нет
     *
     * @return true, если поле пустое и нет возможности для хода
     */
    boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    /**
     * Проверка условий победы с использованием циклов.
     *
     * @param dot символ игрока
     * @return true, если игрок победил
     */
    boolean checkWin(char dot) {
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
    private boolean checkWinHV(int i, int j, char dot, boolean hor) {
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
    private boolean checkWinDiagonal(int i, int j, char dot, boolean leftToRight) {
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
     * Компьютер делает свой ход рядом с фишкой игрока. В некоторых ситуациях это может усложнить игру.
     *
     * @param x координата ячейки x
     * @param y координата ячейки y
     * @return true, если рядом есть фишка соперника
     */
    private boolean isOpponentNear(int x, int y) {
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
