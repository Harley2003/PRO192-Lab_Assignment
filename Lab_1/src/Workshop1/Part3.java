
package workshop1;

import java.util.Scanner;

public class Part3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String list [] = new String [10];
        System.out.println("Enter List Student Name: ");      
        for (int i = 0; i < 10; i++) {
            list [i] = sc.nextLine();
        }
        System.out.println("Ouput:");
        for (int i = 0; i < 10; i++) {
            String upperCase = list[i].substring(0, 1).toUpperCase();
            String lowerCase = list[i].substring(1).toLowerCase();
            list[i] = (upperCase + lowerCase);
            System.out.println(list[i]);
        }
    }    
}
