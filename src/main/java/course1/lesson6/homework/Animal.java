package course1.lesson6.homework;

public class Animal {

    private String name;
    private int runLimit;
    private int swimLimit;
    private static int countAnimals;  // счетчик для созданных животных

    public Animal(String name) {
        this.name = name;
        countAnimals += 1;
    }

    // не создаю сеттер, чтобы не было возможности изменить этот параметр вручную
    public static int getCountAnimals() {
        return countAnimals;
    }

    public int getRunLimit() {
        return runLimit;
    }

    public int getSwimLimit() {
        return swimLimit;
    }

    public void setRunLimit(int runLimit) {
        this.runLimit = runLimit;
    }

    public void setSwimLimit(int swimLimit) {
        this.swimLimit = swimLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Общий метод для бега и плавания
     * @param msg сообщение в консоль о совершенном действии
     * @param distance дистанция
     * @param limit лимит движения
     */
    private void move(String msg, int distance, int limit) {
        if (distance > limit) distance = limit;
        if (distance < 0) distance = 0;
        System.out.println(msg + " " + distance + " м");
    }

    public void run(int distance) {
        move("пробежала", distance, getRunLimit());
    }

    public void swim(int distance) {
        move("проплыла", distance, getSwimLimit());
    }

    @Override
    public String toString() {
        return "Кличка '" + getName() + "'";
    }

    public void info() {
        System.out.println(this);
        System.out.println("Может пробежать " + getRunLimit() + " м.");
        System.out.println("Может проплыть " + getSwimLimit() + " м.");
    }
}
