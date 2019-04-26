package Lesson_8;

import java.util.*;

public class MainTree {

    private HashMap<Integer, Human> humanLookup = new HashMap<Integer, Human>();

    public static class Human {
        private int id;
        private int entityId;
        private int parentId;
        private String name;
        LinkedList<Human> adjacent = new LinkedList<>();

        private Human(int id) {
            this.id = id;
        }
    }

    private Human getHuman(int id) {
//        return humanLookup.get(id);

        if(humanLookup.containsKey(id)) return humanLookup.get(id);
        Human human = new Human(id);
        humanLookup.put(id,human);
        return human;
    }

    public void addEdge(int source, int destination) {
        Human s = getHuman(source);
        Human d = getHuman(destination);
        s.adjacent.add(d);
    }

    public boolean hasPathDFS(int source, int destination) {
        Human s = getHuman(source);
        Human d = getHuman(destination);
        HashSet<Integer> visited = new HashSet<Integer>();
        return hasPathDFS(s, d, visited);
    }

    private boolean hasPathDFS(Human source, Human destination, HashSet<Integer> visited) {
        if (visited.contains(source.id)) {
            return false;
        }
        visited.add(source.id);
        if (source == destination) {
            return true;
        }
        for (Human child: source.adjacent) {
            if (hasPathDFS(child, destination, visited)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {

    }

}
