
package workshop1;

import java.util.Scanner;

public class Part2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.printf("Input the number 1: ");
        float num1 = sc.nextFloat();
        System.out.printf("Input the number 2: ");
        float num2 = sc.nextFloat();
        System.out.println("Input the operator (+-*/): ");
        char op = sc.next().charAt(0);
        
        float result;
        switch (op) {
            case '+':
                result = num1 + num2;
                System.out.println("the result of " + num1 + " " + op + " " + num2 + " = " + result);
                break;
            case '-':
                 result = num1 - num2;
                System.out.println("the result of " + num1 + " " + op + " " + num2 + " = " + result);
                break;
            case '*':
                 result = num1 * num2;
                System.out.println("the result of " + num1 + " " + op + " " + num2 + " = " + result);
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("Error");
                } else {
                 result = num1 / num2;
                System.out.println("the result of " + num1 + " " + op + " " + num2 + " = " + result);
                }
                break;
        }
    }
}
