package Lesson_3.Dop;

import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient{

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String IP_ADRESS = "localhost";
    private int PORT = 7000;
    private Scanner sc;

    private Player player;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ConsoleClient() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
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

    public void inObj(String path) throws IOException {
        ois = new ObjectInputStream(new FileInputStream(path));

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Object obj = null;
                        ois.readObject();
                        System.out.println("Объект отправлен");
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
    }

    public static void main(String[] args) {

        Player p1 = new Player(1, "Bob");
        ConsoleClient cs = new ConsoleClient();
    }

}

