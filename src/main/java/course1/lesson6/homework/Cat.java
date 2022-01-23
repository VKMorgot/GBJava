package course1.lesson6.homework;

public class Cat extends Animal {

    private static int countCats;  // счетчик для созданных кошек

    @Override
    public void run(int distance) {
        System.out.print("Кошка по кличке '" + getName() + "' ");
        super.run(distance);
    }

    // не создаю сеттер, чтобы не было возможности изменить этот параметр вручную
    public static int getCountCats() {
        return countCats;
    }

    // убираю возможность в вызывающей программе менять лимиты
    @Override
    public void setRunLimit(int runLimit) {
        System.out.println("Нельзя переопределять лимит бега");
    }

    // убираю возможность в вызывающей программе менять лимиты
    @Override
    public void setSwimLimit(int swimLimit) {
        System.out.println("Нельзя переопределять лимит плавания");
    }

    @Override
    public void swim(int distance) {
        System.out.print("Кошка по кличке '" + getName() + "' ");
        super.swim(distance);
        System.out.println("Кошки не умеют плавать (хотя на самом деле умеют :) )");
    }

    public Cat(String name) {
        super(name);
        countCats += 1;
        super.setRunLimit(200);
        super.setSwimLimit(0);
    }

    @Override
    public String toString() {
        return "Кошка. " + super.toString();
    }
}
