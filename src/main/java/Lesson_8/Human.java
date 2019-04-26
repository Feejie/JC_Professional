package Lesson_8;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Human {

    private int id;
    private int entityId;
    private int parentId;
    private String name;
    private List<Human> children[];

    public int getEntityId() {
        return entityId;
    }

    public int getParentId() {
        return parentId;
    }

    public Human(int id, int entityId, int parentId, String name) {
        this.id = id;
        this.entityId = entityId;
        this.parentId = parentId;
        this.name = name;
        this.children = new LinkedList[entityId];

//        for (int i = 0; i < entityId + 1; i++) {
//            children[i] = new LinkedList<>();
//        }
    }

    void addEdge(Human entity, Human parent) { //!!!!!!!!!!
        children[parent.getEntityId()].add(entity);
    }

    void dfsUtil(Human entity, boolean visited[]) {
        visited[entity.getEntityId()] = true;
        System.out.println(entity.getEntityId() + " ");

        Iterator it = children[entity.getEntityId()].listIterator(); //!!!!!!!!
        while (it.hasNext()) {
            Human h = (Human) it.next();
            if (!visited[h.getEntityId()]) {
                dfsUtil(h, visited);
            }
        }
    }

    void dfs(Human entity) {
        boolean visited[] = new boolean[this.entityId];

        dfsUtil(entity, visited);
    }


    public static void main(String[] args) {

        Human h1 = new Human(1, 1, 0, "Johnny");
        Human h2 = new Human(2, 2, 0, "Bob");
        Human h3 = new Human(3, 3, 1, "Carl");
        Human h4 = new Human(4, 4, 3, "Lucy");
        Human h5 = new Human(5, 5, 4, "Kate");
        Human h6 = new Human(6, 6, 0, "Richard");

        h3.addEdge(h3, h1);
        h4.addEdge(h4, h3);
        h5.addEdge(h5, h4);


    }


}
