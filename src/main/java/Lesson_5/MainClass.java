package Lesson_5;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainClass {

    public static final int CARS_COUNT = 4;
    public static final CyclicBarrier BARRIER = new CyclicBarrier(CARS_COUNT, new BarrierPoint());
    public static final Semaphore SEMAPHORE = new Semaphore(CARS_COUNT / 2);
    public static final ExecutorService SERVICE = Executors.newFixedThreadPool(CARS_COUNT);
    public static final AtomicInteger AI = new AtomicInteger(0);
    public static String WINNER;

    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        SERVICE.shutdown();
    }

//    Таск, который будет выполняться при достижении сторонами барьера
    public static class BarrierPoint implements Runnable {
        @Override
        public void run() {
            if (AI.intValue() > 0) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!" + " \n"
                        + "Победитель: " + WINNER);
            } else {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            }
        }
    }

}
