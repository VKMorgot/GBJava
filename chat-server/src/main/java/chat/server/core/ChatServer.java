package chat.server.core;

import network.ServerSocketThread;
import network.ServerSocketThreadListener;
import network.SocketThread;
import network.SocketThreadListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {

    private final int SERVER_SOCKET_TIMEOUT = 2000;
    private final DateFormat DATA_FORMAT = new SimpleDateFormat("HH:mm:ss ");
    private Vector<SocketThread> clients = new Vector<>();

    int counter = 0;
    ServerSocketThread server;
    ChatServerListener listener;

    public ChatServer(ChatServerListener listener) {
        this.listener = listener;
    }

    public void start(int port) {
        if (server != null && server.isAlive()) {
            putLog("Server already started");
        } else {
            server = new ServerSocketThread(this, "Chat server " + counter++, port, SERVER_SOCKET_TIMEOUT);
        }
    }

    public void stop() {
        if (server == null || !server.isAlive()) {
            putLog("Server is not running");
        } else {
            server.interrupt();
        }
    }

    /**
     * Логируем событие
     *
     * @param msg текстовое сообщение
     */
    private void putLog(String msg) {
        msg = DATA_FORMAT.format(System.currentTimeMillis()) +
                Thread.currentThread().getName() +
                ": " + msg;
        listener.onChatServerMessage(msg);
    }

    /**
     * Server socket thread methods
     */

    @Override
    public void onServerStart(ServerSocketThread thread) {
        putLog("Server thread started");
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        putLog("Server thread stopped");
    }

    @Override
    public void onServerSocketCreated(ServerSocketThread t, ServerSocket s) {
        putLog("Server socket created");
    }

    @Override
    public void onServerSoTimeout(ServerSocketThread t, ServerSocket s) {
        // Логировать смысла нет, т.к. будет слишком много спама
    }

    @Override
    public void onSocketAccepted(ServerSocketThread t, ServerSocket s, Socket client) {
        // реакция на соединение
        putLog("Client connected");
        String name = "SocketThread" + client.getInetAddress() + ": " + client.getPort();
        new SocketThread(this, name, client);
    }

    @Override
    public void onServerException(ServerSocketThread t, Throwable e) {
        e.printStackTrace();
    }

    /**
     * Socket thread methods
     */

    @Override
    public void onSocketStart(SocketThread t, Socket s) {
        putLog("Client connected");
    }

    @Override
    public void onSocketStop(SocketThread t) {
        putLog("Client disconnected");
        clients.remove(t);
    }

    @Override
    public void onSocketReady(SocketThread t, Socket socket) {
        putLog("Client is ready");
        clients.add(t);
    }

    @Override
    public void onReceiveString(SocketThread t, Socket s, String msg) {
//        t.sendMessage("echo: " + msg);
        for (SocketThread socketThread : clients) {
            socketThread.sendMessage(msg);
        }
    }

    @Override
    public void onSocketException(SocketThread t, Throwable e) {
        e.printStackTrace();
    }
}
