package Lesson_5;

import java.util.concurrent.BrokenBarrierException;

public class Car implements Runnable {

    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private int position;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            MainClass.BARRIER.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            MainClass.AI.incrementAndGet();
            position = MainClass.AI.intValue();

            if (this.getPosition() == 1) {
                MainClass.WINNER = this.name;
            }

            MainClass.BARRIER.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getPosition() {
        return position;
    }
}