package course1.lesson8.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


class ButtonGridClass extends JButton {

    private final int[] dot = new int[2];

    ButtonGridClass(int x, int y) {
        dot[0] = x;
        dot[1] = y;
    }

    int getDotX() {
        return dot[0];
    }

    int getDotY() {
        return dot[1];
    }

}

class GuiClass extends JFrame {

    ButtonGridClass[][] buttons;

    GuiClass(int size) {

        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);

        setLayout(new GridLayout(size, size));
        buttons = new ButtonGridClass[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new ButtonGridClass(i, j);
                add(buttons[i][j]);

                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (TicTacToeGUIAppOnlyDebugMode.humanTurnGUI(buttons[finalI][finalJ].getDotX(), buttons[finalI][finalJ].getDotY())) {
                            buttons[finalI][finalJ].setBackground(Color.green);
                        }
                    }
                });
            }
        }
        setVisible(true);
    }
}

public class TicTacToeGUIAppOnlyDebugMode {

    static boolean isHumanTurn;

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

        GuiClass guiClass = new GuiClass(SIZE);

            while (true) {
                isHumanTurn = true;
                while (isHumanTurn) {
                }

                printMap();
                if (checkWin(DOT_X)) {
                    System.out.println("Игрок победил");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }

                // Здесь нужно поставить breakpoint, чтобы программа работала в режиме дебага
                System.out.println("Breakpoint is needed");

                aiTurn(guiClass);
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
     * Ход человека через интерфейс
     *
     * @param x
     * @param y
     */
    public static boolean humanTurnGUI(int x, int y) {
        if (isCellValid(y, x)) {
            map[x][y] = DOT_X;
            isHumanTurn = false;
            System.out.println("Игрок походил в точку " + (y + 1) + " " + (x + 1));
            return true;
        }
        return false;
    }

    /**
     * Ход компьютера
     */
    public static void aiTurn(GuiClass guiClass) {
            int x, y;
            do {
                x = rnd.nextInt(SIZE);
                y = rnd.nextInt(SIZE);
            } while (!(isCellValid(x, y) && isOpponentNear(x, y)));
            System.out.println("Ход компьютера в точку " + (x + 1) + " " + (y + 1));
            map[y][x] = DOT_O;
            guiClass.buttons[y][x].setBackground(Color.red);
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
