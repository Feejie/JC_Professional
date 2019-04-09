package Lesson_3.MainTask;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Reader {

    private String str;
    private int in;
    private int symOnPage;

    private File file;
    private Scanner sc;

    public Reader(File file) {
        this.file = file;
        this.sc = new Scanner(System.in);

        try (RandomAccessFile raf = new RandomAccessFile(file,"r")){
            setSymOnPage(1800);

            while (true) {
                System.out.println("Введите номер страницы");
                in = sc.nextInt();

                if (in <= raf.length() / symOnPage + 1) {

                    long l;
                    long finishPoint = in * symOnPage;
                    long startPoint = (in - 1) * symOnPage;

                    raf.seek(startPoint);
                    while ((l = raf.getFilePointer()) < finishPoint) {
                        raf.seek(l);
                        str = raf.readLine();

                        if (str == null) {
                            System.out.println("Book is over");
                            break;
                        }
                        System.out.println(str);
                    }

                } else {
                    System.out.println("You read the whole book. Bye");
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSymOnPage(int symOnPage) {
        this.symOnPage = symOnPage;
    }

    public static void main(String[] args) {
        File file = new File("src/main/java/Lesson_3/Files/RedHood.txt");

        new Reader(file);
    }

}
