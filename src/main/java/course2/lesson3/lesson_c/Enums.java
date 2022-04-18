package course2.lesson3.lesson_c;

import java.util.Arrays;

public class Enums {

    enum Color {

        RED("#FF0000"),
        BLUE("#0000FF"),
        GREEN("#00FF00");

        private String code;
        Color(String code) {
            this.code = code;
        }
        public String getCode() {return code;}
    }

    public static void main(String[] args) {

        System.out.println(Color.BLUE);
        System.out.println(Color.GREEN == Color.RED);
        System.out.println(Color.RED.getCode());
        Color[] colors = Color.values();
        System.out.println(Arrays.toString(colors));
    }
}
