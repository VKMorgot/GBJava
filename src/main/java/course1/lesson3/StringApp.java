package course1.lesson3;

public class StringApp {

    public static void main(String[] args) {

        String str = "new string";
        String str2 = new String("new string");

        System.out.println(str.equals(str2));
        System.out.println(str == str2);
    }

}
