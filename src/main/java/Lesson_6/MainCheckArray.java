package Lesson_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MainCheckArray {

    public static void main(String[] args) {

//        Задание 2
        Integer[] fourArr = {5, 8, 4, 9, 1, 4, 1, 2};
        System.out.println(Arrays.toString(afterFourArray(fourArr)));

//        Задание 3
        Integer[] oneFourArr = {1, 4, 1, 1};
        System.out.println(checkOneFour(oneFourArr));

    }

    public static Object[] afterFourArray(Integer[] arr) {
        ArrayList<Integer> al = (ArrayList<Integer>) Arrays.stream(arr)
                .collect(Collectors.toList());

        if (!al.contains(4)) {
            throw new RuntimeException("'4' not found");
        }
        ArrayList<Integer> trg = (ArrayList<Integer>) al.stream()
                .skip(al.lastIndexOf(4) + 1).collect(Collectors.toList());

        return trg.toArray();
    }

    public static boolean checkOneFour(Integer[] arr) {

        return Arrays.stream(arr).anyMatch(x -> x == 4) &&
                Arrays.stream(arr).anyMatch(x -> x == 1) &&
                Arrays.stream(arr).noneMatch(x -> x != 1 && x != 4);

    }

}
