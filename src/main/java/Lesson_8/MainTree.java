package Lesson_8;

import java.util.*;

public class MainTree {

    private HashMap<Integer, Human> humanLookup = new HashMap<Integer, Human>();

    public static class Human {
        private int id;
        private int entityId;
        private int parentId;
        private String name;
        LinkedList<Human> adjacent = new LinkedList<Human>();
        private Human(int id) {
            this.id = id;
        }
    }

    private Human getHuman(int id) {
        return humanLookup.get(id);

//        if(nodeLookup.containsKey(id)) return nodeLookup.get(id);
//        Node node = new Node(id);
//        nodeLookup.put(id,Node);
//        return node;
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
