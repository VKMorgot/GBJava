package course2.lesson4.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Client extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();                                     // поле со всеми сообщениями чата

    // панель для ввода коннекта к серверу: 2 строки, 3 колонки
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");                   // поле для ip адреса
    private final JTextField tfPort = new JTextField("80");                              // порт
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");         // чекбокс "всегда сверху"
    private final JTextField tfLogin = new JTextField("ivan_igorevich");                // логин пользователя
    private final JPasswordField tfPassword = new JPasswordField("123456");        // пароль пользователя
    private final JButton btnLogin = new JButton("Login");                         // кнопка для подключения

    // панель для отправки сообщений и отключения
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("Disconnect");             // кнопка для отключения
    private final JTextField tfMessage = new JTextField();                           // поле для ввода сообщения в чат
    private final JButton btnSend = new JButton("Send");                        // кнопка отправки сообщения
    private final JList<String> userList = new JList<>();                           // список подключенных пользователей

    private final String FILE_NAME = "./src/main/java/course2/lesson4/homework/chat.log";  // файл лога

    private Client() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // посреди экрана
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");
        log.setEditable(false);      // запрещаем ручное редактирование поля чата

        JScrollPane spLog = new JScrollPane(log);                           // подключаем скролирование списка
        JScrollPane spUsers = new JScrollPane(userList);                    // подключаем скролирование списка

        String[] users = {"user1", "user2",
                "user3", "user4", "user5", "user6",
                "user7", "user8", "user9",
                "user10_with_a_exceptionally_long_nickname", };
        userList.setListData(users);
        spUsers.setPreferredSize(new Dimension(100, 0));

        // подключаем ActionListener на элементы формы
        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addActionListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(panelBottom, BorderLayout.SOUTH);
        add(panelTop, BorderLayout.NORTH);
        add(spLog, BorderLayout.CENTER);
        add(spUsers, BorderLayout.EAST);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client();
            }
        });
    }

    /**
     * Проверяем, что сообщение не пустое, и не состоит из одних пробелов
     * @param msg текст сообщения
     * @return true, если сообщение не пустое
     */
    private boolean isMessageCorrect(String msg) {
        return !msg.isBlank();
    }

    /**
     * Пишем сообщение в лог
     * @param msg текст сообщения
     * @param fileName путь к файлу лога
     * @throws IOException
     */
    private void writeLog(String msg, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправляем сообщение в чат
     * @param msg текст сообщения
     */
    private void sendMessage(String msg) {
        if (isMessageCorrect(msg)) {
            log.append(msg + '\n');
            writeLog(msg + '\n', FILE_NAME);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object scr = e.getSource();
        if (scr == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (scr == btnSend || scr == tfMessage) {   // добавляем реакцию на нажатие кнопки и на enter в текстовом поле
            sendMessage(tfMessage.getText());
            tfMessage.setText("");          // очищаем поле ввода
        } else {
            throw new RuntimeException("Action for component unimplemented");
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg = "Exception in thread " + t.getName() +
                " " + e.getClass().getCanonicalName() +
                ": " + e.getMessage() +
                "\n\t" + e.getStackTrace()[0];
        JOptionPane.showMessageDialog(null, msg,
                "Exception", JOptionPane.ERROR_MESSAGE);
    }
}
