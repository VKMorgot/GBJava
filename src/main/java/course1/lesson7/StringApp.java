package course1.lesson7;

import java.util.Arrays;
import java.util.Locale;

public class StringApp {

    public static void main(String[] args) {
        String str = "String";
        System.out.println(str.toCharArray());
        System.out.println(Arrays.toString(str.toCharArray()));

        String str2 = new String(str.toCharArray());
        System.out.println(str2);

        str = str.toUpperCase(Locale.ROOT);
        System.out.println(str);

        System.out.println(str.startsWith("S"));

        System.out.println(str.contains("ing"));
        System.out.println(str.equalsIgnoreCase("string"));

        //=============================================================

        String s = 1 + " " + 2 + " smth else" + 3;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1).append(" ").append(2);

        String newString = stringBuilder.toString();

        // такой же, как StringBuilder
        // потокобезопасный
        // с ним можно работать из двух разных потоков
        // StringBuffer

    }
}
