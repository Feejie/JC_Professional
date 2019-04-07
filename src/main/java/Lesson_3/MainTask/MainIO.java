package Lesson_3.MainTask;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

public class MainIO {

    public static void main(String[] args) {

        // Задание 1
        readToByteArray("src/main/java/Lesson_3/Files/toByte.txt");

        // Задание 2
        File file = new File("src/main/java/Lesson_3/Files/Combi.txt");
        ArrayList<FileReader> al = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            al.add(new FileReader("src/main/java/Lesson_3/Files/1.txt"));
            al.add(new FileReader("src/main/java/Lesson_3/Files/2.txt"));
            al.add(new FileReader("src/main/java/Lesson_3/Files/3.txt"));
            al.add(new FileReader("src/main/java/Lesson_3/Files/4.txt"));
            al.add(new FileReader("src/main/java/Lesson_3/Files/5.txt"));

            Enumeration<FileReader> e = Collections.enumeration(al);

            FileOutputStream fos = null;
            FileReader fr = null;
            FileWriter fw = new FileWriter(file);
            BufferedReader br = null;
            BufferedWriter bw = null;

            while (e.hasMoreElements()) {
                try {
                    br = new BufferedReader(e.nextElement());
                    bw = new BufferedWriter(fw);

                    String currentLine;

                    while ((currentLine = br.readLine()) != null) {
//                        System.out.println(currentLine);
                            bw.write(currentLine);
                    }

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
            br.close();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readToByteArray(String path) {
        if (new File(path).exists()) {
            try (FileInputStream in = new FileInputStream(path)) {
                byte[] arr = new byte[50];
                int x;

//                К виду массива byte
                while ((x = in.read(arr)) != -1) {
                    System.out.println(Arrays.toString(arr));
                }

//                К первоначальному виду (String)
//                while ((x = in.read(arr)) != -1) {
//                    System.out.println(new String(arr, 0, x, "UTF-8"));
//                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Файл не найден");
        }
    }

}
