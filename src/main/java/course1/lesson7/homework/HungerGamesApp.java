package course1.lesson7.homework;

import java.util.Random;

public class HungerGamesApp {

    public static final String[] CATS = {"Барсик", "Мурзик", "Снежок", "Пушок", "Леопольд", "Том", "Васька", "Муська", "Машка"};
    public static final Random rnd = new Random();

    public static void main(String[] args) {

        // Задание №4.
        // Создать массив котов и тарелку с едой,
        // попросить всех котов покушать из этой тарелки,
        // вывести информацию о сытости котов в консоль.
        Plate plate = new Plate(100);
        Cat[] cats = new Cat[CATS.length];
        for (int i = 0; i < CATS.length; i++) {
            cats[i] = new Cat(CATS[i], 10 + rnd.nextInt(20));
            cats[i].eat(plate);
            cats[i].info();
        }
        plate.info();
    }
}
