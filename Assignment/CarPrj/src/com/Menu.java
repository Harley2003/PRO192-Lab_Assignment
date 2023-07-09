package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    public static int int_getChoice(ArrayList<String> options) {
        int response;
        int N = options.size();
        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.print("Please choose an option 1 - " + N + ": ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            response = Integer.parseInt(br.readLine());
            if (response < 1 || response > N) {
                response = -1;
            }
        } catch (IOException e) {
            response = -1;
        }
        return response;
    }

    public static <E> E ref_getChoice(ArrayList<E> options) {
        int response;
        int N = options.size();
        do {
            response = int_getChoice((ArrayList<String>) options);
        } while (response < 0 || response > N);
        return options.get(response - 1);
    }
    
    public Brand ref_getChoice(BrandList options) {
        int response;
        int N = options.size();
        System.out.println("Brand ID List:");
        do {
            response = int_getChoice(options);
        } while ((response < 0) || (response > N));
        return options.get(response - 1);
    }

    public int int_getChoice(BrandList brand) {
        int response;
        int n = brand.size();
        for (int i = 0; i < n; i++) {
            System.out.println("" + (i + 1) + ". " + brand.get(i));
        }
        System.out.print("Please choose an option 1 - " + n + ": ");
        response = scanner.nextInt();
        return response;
    }

}
