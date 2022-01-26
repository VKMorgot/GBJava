package course1.lesson7.homework;

public class Plate {

    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int hunger) {
        // Задание №1.
        // Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды
        // (например, в миске 10 еды, а кот пытается покушать 15-20).
        if (food > hunger) {
            food -= hunger;
            return true; // Задание №2. Если коту хватило еды, сытость true
        }
        return false;  // Задание №3. Считаем, что если коту мало еды в тарелке, то он её просто не трогает.
    }

    public void increaseFood(int food) {
        // Задание №5.
        // Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
        if (food > 0) {
            this.food += food;
        }
    }

    public void info() {
        System.out.println("В тарелке: " + food);
    }
}
