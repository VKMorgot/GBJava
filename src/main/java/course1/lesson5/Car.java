package course1.lesson5;

public class Car {

    public static int wheelsNumber = 0;

    //fields
    private String model;
    private int year;
    private boolean wasCrashed;
    public String[] properties;


    //constructor
    public Car(String model, int year, boolean wasCrashed) {
        this.model = model;
        this.year = year;
        this.wasCrashed = wasCrashed;
    }

    public Car(String model, int year) {
        this(model, year, false);
        this.model += "from overrided constructor";
        /*this.model = model;
        this.year = year;
        this.wasCrashed = false;*/
    }

    public Car(String model) {
        this(model, 2020);
    }

    /**
     * Выводит на экран информацию о машине
     */
    public void info() {
        System.out.println("Model = " + model + " year=" + year + " wasCrashed= " + wasCrashed);
    }

    public void printModel() {
        System.out.println(model);
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public boolean isWasCrashed() {
        return wasCrashed;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        if (year > 0) {
            this.year = year;
        } else {
            System.out.println("Error");
        }
    }

    public void setWasCrashed(boolean wasCrashed) {
        this.wasCrashed = wasCrashed;
    }

    /**
     * Попали в аварию
     */
    public void crash() {
        wasCrashed = true;
    }

}
