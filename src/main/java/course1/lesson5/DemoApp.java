package course1.lesson5;

import java.util.Arrays;

public class DemoApp {

    public static void main(String[] args) {
//        Car car = new Car();
//        car.model = "Dodge";
//        car.year = 2020;
//        car.wheelsNumber = 4;
//        car.setYear(2020);
//        car.setModel("Dodge");

        Car car = new Car("Dodge", 2020);

        car.info();
        car.printModel();

        car.crash();
        car.info();

        String carModel = car.getModel();

//        car.year = -100;

        //Car.info() - нельзя, так как не статический

/*        Car car2 = new Car();
        car2.model = "Ford";
        car2.year = 1990;
        car2.wheelsNumber = 6;

        System.out.println("Model = " + car.model + " year=" + car.year + " wasCrashed= " + car.wasCrashed + " wheelsNumber= " + car.wheelsNumber);
        System.out.println("Model = " + car2.model + " year=" + car2.year + " wasCrashed= " + car2.wasCrashed + " wheelsNumber= " + car2.wheelsNumber);

        int wheelsNumber = Car.wheelsNumber;
        System.out.println(wheelsNumber);

        Car[] cars = new Car[] {car, car2};
        System.out.println(Arrays.toString(cars));*/

        //stringpool
        String str = new String("newString");
    }

}
