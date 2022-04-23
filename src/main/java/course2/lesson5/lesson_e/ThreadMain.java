package course2.lesson5.lesson_e;

public class ThreadMain {

    static class MyTread extends Thread {

        int sleep;

        MyTread(String name, int sleep) {
            super(name);
            this.sleep = sleep;
        }

        void hello() {
            System.out.println("Hello " + Thread.currentThread().getName());
        }

        @Override
        public void run() {
            System.out.println("Hello " + Thread.currentThread().getName());
            // хорошо проектировать так, встраивая флаги для остановки потока
            while (!isInterrupted()) {
//                if (!isInterrupted()) break;
                try {
                    sleep(sleep);
                    System.out.println("Goodbye " + Thread.currentThread().getName());
                    interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ;
        }
    }

    private static long a;
    private static long b;
    private static long c;

    // монитор для управления потоками
    static final Object mon = new Object();

    // synchronized нужен, чтобы не допустить чужие потоки в метод
    private static synchronized void incAll() {
        for (int i = 0; i < 10_000_000; i++) {
            a += 1;
            b += 1;
            c += 1;
        }
        String values = String.format("a=%d, b=%d, c=%d", a, b, c);
        System.out.println(values);
    }

    // можем использовать монитор на часть выполняемого кода
//    private static void incAll() {
//        synchronized (mon) {
//            for (int i = 0; i < 10_000_000; i++) {
//                a += 1;
//                b += 1;
//                c += 1;
//            }
//            String values = String.format("a=%d, b=%d, c=%d", a, b, c);
//            System.out.println(values);
//        }
//    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello " + Thread.currentThread().getName());

        System.out.println("------------запуск потоков-------------");
//        MyTread myTread = new MyTread("My-thread", 10);
//        myTread.hello();
//        myTread.run();
//        myTread.start();
//        System.out.println("-------------");
//        new MyTread("My-thread-2", 10).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello " + Thread.currentThread().getName());
//            }
//        }, "My-thread-3").start();
//        myTread.interrupt();

        System.out.println("------------join() потоков---------------");
//        MyTread mt2 = new MyTread("mt2", 2000);
//        MyTread mt3 = new MyTread("mt3", 3000);
//        MyTread mt1 = new MyTread("mt1", 1000);
//        MyTread mt4 = new MyTread("mt4", 4000);
//
//        mt1.start();
//        mt2.start();
//        mt3.start();
//        mt4.start();
//
//        try {
//            mt1.join();
//            mt2.join();
//            mt3.join();
//            mt4.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//

        System.out.println("----------синхронизация потоков, состояние гонки--------");
//        Runnable r0 = new Runnable() {
//            @Override
//            public void run() {
//                incAll();
//            }
//        };
//
//        new Thread(r0, "tread#1").start();
//        new Thread(r0, "tread#2").start();
//        new Thread(r0, "tread#3").start();

        System.out.println("---------------время выполнения потока------------------");
        Runnable r0 = new Runnable() {
            @Override
            public void run() {
                incAll();
            }
        };

        Thread t = new Thread(r0, "tread#3");
        t.start();
        System.out.println(System.nanoTime());
//        t.join();
        while (t.isAlive()) {}           // замена t.join()
        System.out.println(System.nanoTime());

        System.out.println("Goodbye " + Thread.currentThread().getName());
    }
}
