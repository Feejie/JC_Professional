package Lesson_1;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private float weight;
    private ArrayList<T> contentList;

    public Box() {
        super();
        this.contentList = new ArrayList<T>();
    }

    // Класть фрукты в коробку
    public void addFruit(T fruit) {
        contentList.add(fruit);
    }

    public ArrayList<T> getContentList() {
        return contentList;
    }

    public float getWeight() {
        if (!contentList.isEmpty()) {
            weight = contentList.size() * contentList.get(0).getWeight();
        } else {
            weight = 0;
        }
        return weight;
    }

    // Сравнение коробок
    public boolean compare(Box<?> box) {
        return box.getWeight() == this.getWeight();
    }

    // Пересыпать в другую коробку
    public void replacer(Box<T> boxFor) {
        boxFor.getContentList().addAll(contentList);
        contentList.clear();
    }



    public static void main(String[] args) {

        Box<Apple> appleBox = new Box<Apple>();
        Box<Apple> appleBox2 = new Box<Apple>();
        Box<Orange> orangeBox = new Box<Orange>();

        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();

        appleBox.addFruit(apple1);
        appleBox.addFruit(apple2);
//        appleBox.putFruit(apple3);

        appleBox2.addFruit(apple3);

        orangeBox.addFruit(orange1);
        orangeBox.addFruit(orange2);

        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());

        System.out.println(appleBox.compare(orangeBox));

        System.out.println(appleBox.getWeight());
        System.out.println(appleBox2.getWeight());
        appleBox.replacer(appleBox2);
        System.out.println(appleBox.getWeight());
        System.out.println(appleBox2.getWeight());

    }

}
