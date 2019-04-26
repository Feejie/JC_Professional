package Lesson_7;

public class Human {

    String name;

    public Human() {
        this.name = name;
    }

    @Test (priority = 3)
    public void info2() {
        System.out.println("From USA");
    }

    @Test (priority = 8)
    public void info1() {
        System.out.println("Cherchil");
    }

    @BeforeSuite
    public void bornInfo() {
        System.out.println(1935);
    }

    @AfterSuite
    public void diedInfo() {
        System.out.println(2020);
    }
}
