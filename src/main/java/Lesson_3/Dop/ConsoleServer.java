package Lesson_3.Dop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleServer {

    private ServerSocket server = null;
    private Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner sc;

    private Player player;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ConsoleServer() {
        try {
            server = new ServerSocket(7000);
            System.out.println("Сервер запущен!");

            socket = server.accept();
            System.out.println("Клиент подключился");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);

            ois = new ObjectInputStream(new FileInputStream("player.txt"));

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Object obj = null;
                        try {
                            obj = ois.readObject();
                            System.out.println("Объект прилетел");
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t1.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void outObj(String path) throws IOException {
        oos = new ObjectOutputStream(new FileOutputStream(path));

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Object obj = null;
                        oos.writeObject(obj);
                        System.out.println("Объект отправлен");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
    }


    public static void main(String[] args) {
        new ConsoleServer();
    }
}

