package course1.lesson8.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс для добавления кнопок
 */
class Button extends JButton {

    Button(JFrame frame, String labelText, String borderLayout, Font font) {
        super(labelText);
        super.setFont(font);
        frame.add(this, borderLayout);
    }

    Button(JPanel panel, String labelText, String borderLayout, Font font) {
        super(labelText);
        super.setFont(font);
        panel.add(this, borderLayout);
    }

    void action(int delta, JLabel label, JPanel panel) {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CounterSimpleApp.value += delta;
                label.setText(String.valueOf(CounterSimpleApp.value));
                CounterSimpleApp.validateRange(panel);
            }
        });
    }
}

public class CounterSimpleApp extends JFrame {

    static int value;
    private static Color warningColor;
    private static Color neutralColor;

    static void validateRange(JPanel panel) {
        if (Math.abs(value) > 10) {
            panel.setBackground(warningColor);
        } else {
            panel.setBackground(neutralColor);
        }
    }

    public CounterSimpleApp() {

        // основное окно программы
        setBounds(0, 0, 350, 120);
        setTitle("Простой счетчик");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // шрифт
        Font font = new Font("Arial", Font.BOLD, 32);

        // центральная панель
        // на центральной панели будут находиться значения счетчика и две кнопки по его одиночному уменьшению/увеличению
        // кнопки множественного увеличения будут лежать на основном фрейме
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // текстовое поле (нередактируемое)
        JLabel label = new JLabel(String.valueOf(value));
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        // цвета панели в разных состояниях
        neutralColor = panel.getBackground();
        warningColor = new Color(255, 0, 0);
        panel.add(label, BorderLayout.CENTER);

        // кнопка одиночного уменьшения
        Button decrementOneButton = new Button(panel, "<", BorderLayout.WEST, font);
        decrementOneButton.action(-1, label, panel);

        // кнопка одиночного увеличения
        Button incrementOneButton = new Button(panel, ">", BorderLayout.EAST, font);
        incrementOneButton.action(1, label, panel);

        // кнопка множественного уменьшения (на 5)
        Button decrementFiveButton = new Button(this, "<<", BorderLayout.WEST, font);
        decrementFiveButton.action(-5, label, panel);

        // кнопка множественного увеличения (на 5)
        Button incrementFiveButton = new Button(this, ">>", BorderLayout.EAST, font);
        incrementFiveButton.action(5, label, panel);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CounterSimpleApp();
    }
}
