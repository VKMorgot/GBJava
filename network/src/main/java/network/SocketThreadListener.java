package network;

import java.net.Socket;

public interface SocketThreadListener {
    void onSocketStart(SocketThread t, Socket s);
    void onSocketStop(SocketThread t);
    void onSocketReady(SocketThread t, Socket socket);
    void onReceiveString(SocketThread thread, Socket s, String msg);
    void onSocketException(SocketThread t, Throwable e);
}
