package StudentManager;

import java.util.Scanner;

public class Inputter {

    public static Scanner sc = new Scanner(System.in);

    public static int inputInt(String msg, int min, int max) {
        if (min > max) {
            int t = min;
            min = max;
            max = t;
        }
        int data;
        do {
            System.out.println(msg);
            data = Integer.parseInt(sc.nextLine());
        } while (data < min || data > max);
        return data;
    }

    public static String inputStr(String msg) {
        System.out.println(msg);
        String data = sc.nextLine().trim();
        return data;
    }

    public static String inputNonBlankStr(String msg) {
        String data;
        do {
            System.out.println(msg);
            data = sc.nextLine();
        } while (data.length() == 0);
        return data;
    }

    static String inputPattern(String msg, String pattern) {
        String str = null;

        do {
            str = inputStr(msg);
        } while (!str.matches(pattern));

        return str;
    }
}
