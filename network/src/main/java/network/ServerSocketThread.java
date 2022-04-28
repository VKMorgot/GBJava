package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Класс занимается созданием сокетов: ожидает подключения, блокирует порт,
 * блокирует поток программы, дожидается подключения и создает сокет
 */
public class ServerSocketThread extends Thread {
    private int port;
    private int timeout;
    private ServerSocketThreadListener listener;

    public ServerSocketThread(ServerSocketThreadListener listener, String name, int port, int timeout) {
        super(name);
        this.port = port;
        this.timeout = timeout;
        this.listener = listener;
        start();
    }

    @Override
    public void run() {
        listener.onServerStart(this);
        try (ServerSocket server = new ServerSocket(port)) {
            server.setSoTimeout(timeout);  // ожидаем выполнение server.accept заданное в timeout время
            listener.onServerSocketCreated(this, server);
            while (!isInterrupted()) {
                Socket client;
                try {
                    client = server.accept(); // После истечения timeout, отпускаем accept и бросаем исключение. Без setSoTimeout исключения бы не было
                } catch (SocketTimeoutException e) {
                    listener.onServerSoTimeout(this, server);
                    continue;
                }
                listener.onSocketAccepted(this, server, client);
            }
        } catch (IOException e) {
            listener.onServerException(this, e);
        } finally {
            listener.onServerStop(this);
        }
    }
}
