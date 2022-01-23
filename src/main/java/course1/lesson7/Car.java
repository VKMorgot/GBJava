package course1.lesson7;

public class Car {
    /**
     * Объем бензобака
     */
    public int volume;

    public Car (int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    // здесь основная идея урока: в один метод класса передаем метод другого класса
    public void takeGasoline(GasStation station) {
        station.refill(volume);
    }

    public void findGasoline(GasStation[] stations) {
        for (GasStation station : stations) {
            if (station.getAvailable() < volume) {
                continue;
            }
            station.refill(volume);
            break;
        }
    }
}
