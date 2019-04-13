package Lesson_4;

public class MainThread {

    public static void main(String[] args) {

        final Object obj = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    for (int i = 0; i < 5; i++) {
                        obj.notifyAll();
                        System.out.print("A ");
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    for (int i = 0; i < 5; i++) {
                        System.out.print("B ");
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    for (int i = 0; i < 5; i++) {
                        obj.notify();
                        System.out.print("C ");
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }
}


