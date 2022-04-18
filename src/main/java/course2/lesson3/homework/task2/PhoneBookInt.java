package course2.lesson3.homework.task2;

import java.util.ArrayList;

public interface PhoneBookInt {

    void add(String lastName, String phoneNumber);
    ArrayList<String> get(String lastName);
}
