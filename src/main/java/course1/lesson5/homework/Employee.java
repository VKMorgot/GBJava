package course1.lesson5.homework;

public class Employee {

    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    public Employee(String fullName, String position, String email, String phoneNumber, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary > 0) {
            this.salary = salary;
        }
        else System.out.println("Зарплата не может быть меньше или равна 0");

    }

    public int getAge() {
        return age;
    }

    /**
     * Считаем, что возраст не может быть отрицательным или больше 100
     * @param age возраст сотрудника
     */
    public void setAge(int age) {
        if (age > 0 && age < 100) {
            this.age = age;
        }
        else System.out.println("Возраст не может быть меньше нуля и больше 100");
    }

    /**
     * Вывод информации о сотруднике
     */
    public void info() {
        System.out.println("Информация по сотруднику");
        System.out.println("Имя: " + fullName);
        System.out.println("Должность: " + position);
        System.out.println("Электронная почта: " + email);
        System.out.println("Номер телефона: " + phoneNumber);
        System.out.println("Зарплата: " + salary);
        System.out.println("Возраст: " + age);
    }
}
