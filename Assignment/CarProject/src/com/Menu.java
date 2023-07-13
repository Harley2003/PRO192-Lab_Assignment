package com;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private int response;
    Scanner sc = new Scanner(System.in);

    public int int_getChoice(ArrayList<String> options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.printf("Please choose an optiopn 1 - 11: ");
        response = sc.nextInt();
        return response;
    }

    public int int_getChoice(BrandList brand) {
        int n = brand.size();
        for (int i = 0; i < n; i++) {
            System.out.println("" + (i + 1) + ". " + brand.get(i));
        }
        System.out.printf("Please choose an option 1 - 12: ");
        response = sc.nextInt();
        return response;
    }

    public Brand ref_getChoice(BrandList options) {
        int N = options.size();
        System.out.printf("Brand ID List: \n");
        do {
            response = int_getChoice(options);
        } while ((response < 0) || (response > N));
        return options.get(response - 1);
    }
}
