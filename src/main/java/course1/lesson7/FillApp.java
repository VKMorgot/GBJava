package course1.lesson7;

import java.util.Arrays;

public class FillApp {

    public static void main(String[] args) {
        Car car = new Car(60);
        Car car2 = new Car(100);
        GasStation gasStation = new GasStation(1000);

        gasStation.info();

//        gasStation.setAvailable(gasStation.getAvailable() - car.getVolume());
//        gasStation.setAvailable(gasStation.getAvailable() - car2.getVolume());
//
//        gasStation.refill(car.getVolume());
//        gasStation.refill(car2.getVolume());

        car.takeGasoline(gasStation);
        car2.takeGasoline(gasStation);

        gasStation.info();
    }
}
