package Lesson_7;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MainAnnotation {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Class h = Human.class;

        try {
            start(h);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public static void start(Class c) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        Method[] m = c.getDeclaredMethods();
        List<Method> mList = new ArrayList<>();

        Constructor constructor = c.getConstructor();
        Object obj = constructor.newInstance();

        for (Method o: m) {
            if(o.isAnnotationPresent(Test.class)) {
                mList.add(o);
            }
        }

        mList.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
            }
        });

        for (Method o: m) {
            if(o.isAnnotationPresent(BeforeSuite.class)) {
                if (mList.get(0).isAnnotationPresent(BeforeSuite.class)) {
                    throw new RuntimeException("BeforeSuit already exists");
                }
                mList.add(0, o);
            }

            if(o.isAnnotationPresent(AfterSuite.class)) {
                if (mList.get(mList.size() - 1).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("AfterSuit already exists");
                }
                mList.add(o);
            }
        }

        for (Method o: mList) {
            o.invoke(obj);
        }

    }


}
