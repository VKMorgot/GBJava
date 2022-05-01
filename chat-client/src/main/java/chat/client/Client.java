package chat.client;

import chat.common.Messages;
import network.SocketThread;
import network.SocketThreadListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Client extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, SocketThreadListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;

    private boolean shownIOErrors = false;                      // для контроля возникновения ошибок
    private boolean showPopupError = true;                      // определяем, нужно ли показывать ошибку в попапе
    private SocketThread socketThread;

    private final String FILE_NAME = "./chat-client/src/main/java/chat/client/chat.log";       // файл для записи лога чата
    private static final DateFormat DATA_FORMAT = new SimpleDateFormat("HH:mm:ss ");
    private static final String TITLE = "Chat Client";

//    private final String tutorIP = "95.84.209.91"           // ip преподавателя
//    private final String tutorPort = "8189"                 // port преподавателя

    private final JTextArea log = new JTextArea();                                     // поле со всеми сообщениями чата

    // панель для ввода коннекта к серверу: 2 строки, 3 колонки
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");                   // поле для ip адреса
    private final JTextField tfPort = new JTextField("80");                              // порт
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");         // чекбокс "всегда сверху"
    private final JTextField tfLogin = new JTextField("VKMorgot");                      // логин пользователя
    private final JPasswordField tfPassword = new JPasswordField("123");        // пароль пользователя
    private final JButton btnLogin = new JButton("Login");                         // кнопка для подключения

    // панель для отправки сообщений и отключения
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("Disconnect");             // кнопка для отключения
    private final JTextField tfMessage = new JTextField();                           // поле для ввода сообщения в чат
    private final JButton btnSend = new JButton("<html><b>Send</b></html>");    // кнопка отправки сообщения
    private final JList<String> userList = new JList<>();                           // список подключенных пользователей

    private Client() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // посреди экрана
        setSize(WIDTH, HEIGHT);
        setTitle(TITLE);
        log.setEditable(false);      // запрещаем ручное редактирование поля чата
        log.setLineWrap(true);       // добавляем перенос по строкам

        JScrollPane spLog = new JScrollPane(log);             // подключаем скролирование списка
        JScrollPane spUsers = new JScrollPane(userList);      // подключаем скролирование списка

        spUsers.setPreferredSize(new Dimension(100, 0));

        // подключаем ActionListener на элементы формы
        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addActionListener(this);
        btnLogin.addActionListener(this);
        btnDisconnect.addActionListener(this);

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


        panelBottom.setVisible(false);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object scr = e.getSource();
        if (scr == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (scr == btnSend || scr == tfMessage) {
            sendMessage();
        } else if (scr == btnLogin) {
            connect();
        } else if (scr == btnDisconnect) {
            showPopupError = false;
            socketThread.close();
//            showPopupError = true;
        } else {
            throw new RuntimeException("Action for component unimplemented");
        }
    }

    private void connect() {
        try {
            Socket socket = new Socket(tfIPAddress.getText(), Integer.parseInt(tfPort.getText()));
            socketThread = new SocketThread(this, "Client", socket);
        } catch (IOException e) {
            showException(Thread.currentThread(), e);
        }
    }

    private void sendMessage() {
        String msg = tfMessage.getText();
        String userName = tfLogin.getText();
        tfMessage.requestFocusInWindow();
        if (msg.isBlank()) {
            return;
        }
        tfMessage.setText(null);
        socketThread.sendMessage(Messages.getTypeBcastFromClient(msg));
//        putLog(String.format("%s: %s", userName, msg));
//        wrtMsgToLogFile(msg, userName);

    }

    private void wrtMsgToLogFile(String msg, String userName) {
        try (FileWriter out = new FileWriter(FILE_NAME, true)) {
            out.write(userName + ": " + msg + "\n");
            out.flush();
        } catch (IOException e) {
            if (!shownIOErrors) {
                shownIOErrors = true;
                showException(Thread.currentThread(), e);
            }
        }
    }

    private void putLog(String msg) {
        if ("".equals(msg)) return;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }

    private void showException(Thread t, Throwable e) {
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        if (ste.length == 0) {
            msg = "Empty Stacktrace";
        } else {
            msg = String.format("Exception in \"%s\" %s: %s\n\tat %s",
                    t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        }
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
//        showException(t, e);
    }

    @Override
    public void onSocketStart(SocketThread t, Socket s) {
        putLog("Start");
    }

    @Override
    public void onSocketStop(SocketThread t) {
        panelBottom.setVisible(false);
        panelTop.setVisible(true);
        setTitle(TITLE);
        userList.setListData(new String[0]);
    }

    @Override
    public void onSocketReady(SocketThread t, Socket socket) {
        panelBottom.setVisible(true);
        panelTop.setVisible(false);
        String login = tfLogin.getText();
        String pass = new String(tfPassword.getPassword());
        t.sendMessage(Messages.getAuthRequest(login, pass));
    }

    @Override
    public void onReceiveString(SocketThread thread, Socket s, String msg) {
        handleMessage(msg);
    }

    void handleMessage(String value) {
        String[] arr = value.split(Messages.DELIMITER);
        String msgType = arr[0];
        switch (msgType) {
            case Messages.AUTH_ACCEPT:
                setTitle(TITLE + " logged in as: " + arr[1]);
                break;
            case Messages.AUTH_DENY:
                putLog(value);
                break;
            case Messages.MSG_FORMAT_ERROR:
                putLog(value);
                break;
            case Messages.USER_LIST:
                String users = value.substring(Messages.DELIMITER.length() +
                        Messages.USER_LIST.length());
                String[] usersArr = users.split(Messages.DELIMITER);
                Arrays.sort(usersArr);
                userList.setListData(usersArr);
                break;
            case Messages.MSG_BROADCAST:
                log.append(DATA_FORMAT.format(Long.parseLong(arr[1])) + ": " + arr[2] + ": " + arr[3] + "\n");
                log.setCaretPosition(log.getDocument().getLength());
                break;
            default:
                throw new RuntimeException("Unknown message type: " + msgType);
        }
    }

    @Override
    public void onSocketException(SocketThread t, Throwable e) {
        if (showPopupError)
            showException(t, e);
        else
            showPopupError = true;
    }
}
