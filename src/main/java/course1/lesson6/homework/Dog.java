package course1.lesson6.homework;

public class Dog extends Animal {

    private static int countDogs;  // счетчик для созданных собак

    @Override
    public void run(int distance) {
        System.out.print("Собака по кличке '" + getName() + "' ");
        super.run(distance);
    }

    // не создаю сеттер, чтобы не было возможности изменить этот параметр вручную
    public static int getCountDogs() {
        return countDogs;
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
        System.out.print("Собака по кличке '" + getName() + "' ");
        super.swim(distance);
    }

    public Dog(String name) {
        super(name);
        countDogs += 1;
        super.setRunLimit(500);
        super.setSwimLimit(10);
    }

    @Override
    public String toString() {
        return "Собака. " + super.toString();
    }
}
