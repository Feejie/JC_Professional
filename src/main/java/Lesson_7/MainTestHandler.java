package Lesson_7;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;

public class MainTestHandler {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, MalformedURLException, ClassNotFoundException {

        File folder = new File("A:/Downloads/HW(J)/HWs");

        String[] file = folder.list();

        ArrayList<String> fileList = new ArrayList<>();

        for (String o: file) {
            String[] mass = o.split("\\.");

            if (mass[1].equalsIgnoreCase("class")) {
                fileList.add(mass[0]);
            }
        }

        Iterator it = fileList.iterator();

        while (it.hasNext()) {
            String name = String.valueOf(it.next());
            Class ch = URLClassLoader.newInstance(new URL[]{new File("A:/Downloads/HW(J)/HWs").toURL()})
                    .loadClass(name);

            Constructor constructor = ch.getConstructor();
            Object calc = constructor.newInstance();

            Method m1 = ch.getDeclaredMethod("calculate",int.class,int.class,int.class,int.class);
            m1.setAccessible(true);
            int res1 = (Integer) m1.invoke(calc,1,1,1,1);
            System.out.println(res1);

            Method m2 = ch.getDeclaredMethod("checkTwoNumbers",int.class,int.class);
            m2.setAccessible(true);
            boolean res2 = (boolean) m2.invoke(calc,6,6);
            System.out.println(res2);

            Method m3 = ch.getDeclaredMethod("printIsPositive",int.class);
            m3.setAccessible(true);
            String res3 = (String) m3.invoke(calc,2);
            System.out.println(res3);

            Method m4 = ch.getDeclaredMethod("isNegative",int.class);
            m4.setAccessible(true);
            boolean res4 = (boolean) m4.invoke(calc,-3);
            System.out.println(res4);

            Method m5 = ch.getDeclaredMethod("printWelocome",String.class);
            m5.setAccessible(true);
            String res5 = (String) m5.invoke(calc,"Johnny");
            System.out.println(res5);

            Method m6 = ch.getDeclaredMethod("isLeapYear",int.class);
            m6.setAccessible(true);
            boolean res6 = (boolean) m6.invoke(calc,2020);
            System.out.println(res6);

            if (res1 == 2 && res2 && res3 == "Your number is positive!" && res4 && res5 == "Привет, Johnny!" &&
                    res6) {
                System.out.println(name + " passed");
            } else {
                System.out.println(name + " faild");
            }
            System.out.println();

        }

    }

}
