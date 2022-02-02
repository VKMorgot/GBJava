package course1.lesson7.homework;

public class Cat {

    private String name;
    private int appetite;

    // Задание №2
    // Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны).
    // Если коту удалось покушать (хватило еды), сытость = true.
    private boolean isFull;

    public boolean isFull() {
        return isFull;
    }

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        isFull = false; // Задание №2. Когда создаем котов, они голодны.
    }

    public String getName() {
        return name;
    }

    public void eat(Plate p) {
        isFull = p.decreaseFood(appetite); // Задание №2. Если коту хватило еды, сытость true
    }

    public void info() {
        if (isFull()) {
            System.out.println("Кот " + getName() + " сыт");
        } else {
            System.out.println("Кот " + getName() + " голоден");
        }
    }
}
