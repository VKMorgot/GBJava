package course2.lesson6.lesson_f;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8189);
             Socket client = server.accept()) {
//            Socket client = server.accept();  // перенесли в try-with-recourses
            System.out.println("client connected");
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            while (true) {
                String msg = in.readUTF();
                out.writeUTF("echo: " + msg);
            }
//            client.close();   // если расширить try-with-recourses, то закрывать уже не надо
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
