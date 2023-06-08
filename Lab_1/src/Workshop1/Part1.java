
package workshop1;

import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.printf("Enter number of columns: ");
        int columns = sc.nextInt();
        
        int matrix [] [] = new int [rows] [columns];
        System.out.printf("Enter the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("\n m [" + i + "] [" + j + "] = ");
                matrix [i] [j] = sc.nextInt();
            }          
        }
        System.out.println("Matrix Inputted:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("%3d", matrix [i] [j]);
            }
            System.out.println("\n");
         }
        
        int sum = 0;
         for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sum += matrix [i] [j];
            }
         }
         System.out.println("Sum: " + sum);
        
         System.out.println("Average: " + (float) sum / (rows * columns));
     }   
}
