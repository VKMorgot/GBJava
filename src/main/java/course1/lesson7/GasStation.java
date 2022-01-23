package course1.lesson7;

public class GasStation {

    private int available;

    public GasStation (int available) {
        this.available = available;
    }

    /**
     * Заправить указанное кол-во литров
     * @param amount
     */
    public void refill(int amount) {
        this.available -= amount;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "available=" + available +
                '}';
    }

    public void info() {
        System.out.println(this);
    }

}
