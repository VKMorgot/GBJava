package course1.lesson6.homework;

import java.util.Arrays;

public class PetsApp {

    // массивы кличек
    public static String[] DOGS = {"Бобик", "Трезор", "Мухтар", "Снежок", "Артемон"};
    public static String[] CATS = {"Снежок", "Толстопуз", "Леопольд", "Барсик", "Мурзик", "Рыжик", "Том"};
    public static String[] ANIMALS = {"Мартин", "Джерри", "Мелвин", "Шкипер", "Бэмби", "Джамбо", "Бродяга", "Литл"};

    public static void adoptPets(Animal[] pets, String[] petNames) {
        for (int i = 0; i < petNames.length; i++) {
            pets[i] = new Animal(petNames[i]);
            System.out.println(pets[i]);
        }
    }

    public static void adoptPets(Cat[] cats, String[] petNames) {
        for (int i = 0; i < petNames.length; i++) {
            cats[i] = new Cat(petNames[i]);
            System.out.println(cats[i]);
        }
    }

    public static void adoptPets(Dog[] dogs, String[] petNames) {
        for (int i = 0; i < petNames.length; i++) {
            dogs[i] = new Dog(petNames[i]);
            System.out.println(dogs[i]);
        }
    }

    public static void main(String[] args) {

        // Задание №4*. Добавить подсчет созданных котов, собак и животных.

        // создаем массив кошек
        Cat[] cats = new Cat[CATS.length];
        for (int i = 0; i < CATS.length; i++) {
            cats[i] = new Cat(CATS[i]);
        }

        // создаем массив собак
        Dog[] dogs = new Dog[DOGS.length];
        for (int i = 0; i < DOGS.length; i++) {
            dogs[i] = new Dog(DOGS[i]);
        }

        // создаем массив оставшихся животных
        Animal[] animals = new Animal[ANIMALS.length];
        for (int i = 0; i < ANIMALS.length; i++) {
            animals[i] = new Animal(ANIMALS[i]);
        }

        // второй спобос создать массив кошек, собак и животных
        Cat[] catsAdopted = new Cat[CATS.length];
        adoptPets(catsAdopted, CATS);

        Dog[] dogsAdopted = new Dog[DOGS.length];
        adoptPets(dogsAdopted, DOGS);

        Animal[] animalsAdopted = new Animal[ANIMALS.length];
        adoptPets(animalsAdopted, ANIMALS);

        System.out.println(Arrays.toString(catsAdopted));
        System.out.println(Arrays.toString(cats));
        System.out.println(Arrays.toString(dogsAdopted));
        System.out.println(Arrays.toString(dogs));
        System.out.println(Arrays.toString(animalsAdopted));
        System.out.println(Arrays.toString(animals));
        System.out.println();

        // загоняем всех животных в один массив
        Animal[] allAnimals = new Animal[cats.length + dogs.length + animals.length];
        System.arraycopy(cats, 0, allAnimals, 0, cats.length);
        System.arraycopy(dogs, 0, allAnimals, cats.length, dogs.length);
        System.arraycopy(animals, 0, allAnimals, cats.length + dogs.length, animals.length);

        // считаем, сколько каких животных у нас в объединенном массиве
        int countCats = 0, countDogs = 0, countAnimals = 0;
        for (Animal allAnimal : allAnimals) {
            if (allAnimal instanceof Cat) countCats += 1;
            if (allAnimal instanceof Dog) countDogs += 1;
            if (allAnimal instanceof Animal) countAnimals += 1;
        }

        System.out.println("Первый способ подсчета через instanceof");

        System.out.println("Всего кошек: " + countCats);
        System.out.println("Всего собак: " + countDogs);
        System.out.println("Всего животных (включая кошек и собак): " + countAnimals);

        System.out.println();
        System.out.println("Второй способ подсчета через статические переменные");

        System.out.println("Всего кошек: " + Cat.getCountCats());
        System.out.println("Всего собак: " + Dog.getCountDogs());
        System.out.println("Всего животных (включая кошек и собак): " + Animal.getCountAnimals());

        // Проверка работы вывода инфы о собаках, кошках и их спортивных достижениях
        System.out.println();
        cats[1].run(35);
        cats[1].swim(2);
        cats[1].info();

        System.out.println();
        dogs[3].setRunLimit(3);
        dogs[3].run(300);
        dogs[3].info();
    }
}
