package course1.lesson8.homework.TicTacToeNew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Кнопка на панели интерфейса
 */
class ButtonGrid extends JButton {

    // координаты кнопки на панели интерфейса
    private final int[] dot = new int[2];

    ButtonGrid(int x, int y) {
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

/**
 * Графический интерфейс программы
 */
class Gui extends JFrame {

    // кнопки игрового поля
    private final ButtonGrid[][] buttons;

    // логика игры
    private final TicTacToe ticTacToe;

    // для удобства записи координат x и y
    private final int X = 0;
    private final int Y = 1;

    // размер игрового поля
    private int size = 0;

    // состояние игры: true - если игра закончена
    private final AtomicBoolean isGameOver = new AtomicBoolean();

    /**
     * Задаем размер игрового поля и рисуем форму с кнопками
     */
    Gui() {

        String message = "Добро пожаловать в игру \"Крестики-Нолики\"";
        System.out.println(message);

        String[] strSize = {"3х3", "4х4", "5х5"};
        String inputSize = (String) JOptionPane.showInputDialog(null, message + "\nВыберите размер поля", TicTacToe.TITLE, JOptionPane.PLAIN_MESSAGE, null, strSize, strSize[0]);

        // число точек для победы. зависит от размера игрового поля
        int DOTS_TO_WIN = 0;
        if (inputSize != null) {
            if (inputSize.equals(strSize[0])) {
                size = 3;
                DOTS_TO_WIN = 3;
            }
            if (inputSize.equals(strSize[1])) {
                size = 4;
                DOTS_TO_WIN = 4;
            }
            if (inputSize.equals(strSize[2])) {
                size = 5;
                DOTS_TO_WIN = 4;
            }
        } else System.exit(0);

        setTitle(TicTacToe.TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(size, size));
        buttons = new ButtonGrid[size][size];

        ticTacToe = new TicTacToe(size, DOTS_TO_WIN);
        isGameOver.set(false);
        drawGame();
        setVisible(true);
    }

    /**
     * Рисуем кнопки на форме. На каждую кнопку добавляет ActionListener
     * В ActionListener добавляем логику работы игры
     */
    private void drawGame() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new ButtonGrid(i, j);
                add(buttons[i][j]);

                int finalI = i;
                int finalJ = j;

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // выполняем, если игра не закончена и ход человека валидный
                        if (!isGameOver.get() && ticTacToe.humanTurnGUI(buttons[finalI][finalJ].getDotX(), buttons[finalI][finalJ].getDotY())) {
                            buttons[finalI][finalJ].setBackground(Color.green);

                            ticTacToe.printMap();
                            if (ticTacToe.checkWin(ticTacToe.DOT_X)) {
                                System.out.println("Игрок победил");
                                isGameOver.set(true);
                                JOptionPane.showMessageDialog(null, "Игрок победил", TicTacToe.TITLE, JOptionPane.INFORMATION_MESSAGE);
                                restartGame();
                                return;
                            }

                            if (ticTacToe.isMapFull()) {
                                System.out.println("Ничья");
                                isGameOver.set(true);
                                JOptionPane.showMessageDialog(null, "Ничья", TicTacToe.TITLE, JOptionPane.INFORMATION_MESSAGE);
                                restartGame();
                                return;
                            }

                            int[] dot = ticTacToe.aiTurn();
                            buttons[dot[Y]][dot[X]].setBackground(Color.red);
                            ticTacToe.printMap();
                            if (ticTacToe.checkWin(ticTacToe.DOT_O)) {
                                System.out.println("Компьютер победил");
                                isGameOver.set(true);
                                JOptionPane.showMessageDialog(null, "Компьютер победил", TicTacToe.TITLE, JOptionPane.INFORMATION_MESSAGE);
                                restartGame();
                                return;
                            }
                            if (ticTacToe.isMapFull()) {
                                System.out.println("Ничья");
                                isGameOver.set(true);
                                JOptionPane.showMessageDialog(null, "Ничья", TicTacToe.TITLE, JOptionPane.INFORMATION_MESSAGE);
                                restartGame();
                                return;
                            }
                        }
                    }
                });
            }
        }
    }

    private void restartGame() {
        switch (JOptionPane.showConfirmDialog(null, "Хотите сыграть еще раз?", TicTacToe.TITLE, JOptionPane.YES_NO_OPTION)) {
            case JOptionPane.YES_OPTION : {
                setVisible(false);
                new Gui();
                break;
            }
            case JOptionPane.NO_OPTION: {
                System.exit(0);
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Gui();
    }
}

