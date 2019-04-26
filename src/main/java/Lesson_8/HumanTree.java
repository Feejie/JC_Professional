package Lesson_8;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HumanTree {
    private static HashMap<Integer, Human> humanLookup = new HashMap<>();

    public static class Human {
        private int id;
        private int parentId;
        private String name;
        private List<Human> children;

        public Human(int id, int parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
            this.children = new LinkedList<>();

            humanLookup.put(id, this);
            if (humanLookup.containsKey(parentId)) {
                humanLookup.get(parentId).children.add(this);
            }
        }

        public List<Human> getChildren() {
            return children;
        }

        public String info() {
            return id + " " + name;
        }
    }

    public static HashMap<Integer, Human> getHumanLookup() {
        return humanLookup;
    }


    public static void main(String[] args) {
        Human h1 = new Human(1, 0, "Johnny");
        Human h2 = new Human(2, 0, "Bob");
        Human h3 = new Human(3, 1, "Lucy");
        Human h4 = new Human(4, 3, "Carl");
        Human h5 = new Human(5, 4, "Kate");
        Human h6 = new Human(6, 0, "Richard");

        System.out.println(h1.getChildren().get(0).getChildren().get(0).name);
    }

}
