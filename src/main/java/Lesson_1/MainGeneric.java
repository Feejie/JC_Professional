package Lesson_1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainGeneric<T> {

    private T obj;
    private T[] arr;
    private ArrayList<T> arrayList;

    public MainGeneric(T[] arr) {
        this.arrayList = new ArrayList<T>();
        this.arr = arr;
    }

    // Поменять элементы массива местами
    public void arrayReplacer(T obj1, T obj2) {
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(obj1)) {
                index1 = i;
            }
            if (arr[i].equals(obj2)) {
                index2 = i;
            }
        }
        obj = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = obj;
    }

    public void toList() {
        arrayList.addAll(Arrays.asList(arr));
    }

    // Поменять элементы местами в ArrayList
//    public void listReplacer(T obj1, T obj2) {
//        int index1, index2;
//        index1 = arrayList.indexOf(obj1);
//        index2 = arrayList.indexOf(obj2);
//        obj = arrayList.get(index1);
//        arrayList.set(index1, obj2);
//        arrayList.set(index2, obj);
//    }

    public T[] getArr() {
        return arr;
    }

    public ArrayList getArrayList() {
        return arrayList;
    }



    public static void main(String[] args) {
        String[] strings = {"str1", "str2", "str3", "str4"};
        MainGeneric<String> mg = new MainGeneric<String>(strings);

        mg.arrayReplacer("str2", "str3");
        System.out.println("Array: " + Arrays.toString(mg.getArr()));

        mg.toList();
        System.out.println("ArrayList: " + mg.getArrayList());

//        mg.listReplacer("str3", "str2");
//        System.out.println("ReArrayList: " + mg.getArrayList());
    }

}
