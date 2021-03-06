package course2.lesson5.lesson_e.chat;

public class ServerSocketThread extends Thread {
    private int port;

    ServerSocketThread(String name, int port) {
        super(name);
        this.port = port;
        start();
    }

    @Override
    public void run() {
        System.out.println("Server started");
        while (!isInterrupted()) {
//            System.out.println("Server is running");
            System.out.println(getName() + " is running");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
        System.out.println("Server stopped");
    }
}
