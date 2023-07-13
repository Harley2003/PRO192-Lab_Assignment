package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BrandList extends ArrayList<Brand> {

    private String brandID, brandName, soundBrand;
    private double price;
    private int pos;
    Scanner sc = new Scanner(System.in);
    BufferedReader br;
    PrintWriter pw;

    public boolean loadFromFile(String fileName) throws IOException {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("resource\\" + fileName);
            br = new BufferedReader(new InputStreamReader(inputStream));
            String arr[];
            String line = br.readLine();
            while (line != null) {
                arr = line.split(",");
                brandID = arr[0].trim();
                brandName = arr[1].trim();
                soundBrand = arr[2].split(":")[0].trim();
                price = Double.parseDouble(arr[2].split(":")[1].trim());
                this.add(new Brand(brandID, brandName, soundBrand, price));
                line = br.readLine();
            }
            br.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found!");
        }
        return false;
    }

    public boolean saveToFile(String fileName) throws IOException {
        pw = new PrintWriter(new FileWriter(fileName));
        for (Brand i : this) {
            pw.println(i);
        }
        pw.close();
        return true;
    }

    public int searchID(String brandID) {
        for (int i = 0; i < this.size(); i++) {
            if (brandID.equals(this.get(i).getBrandID())) {
                return i;
            }
        }
        return -1;
    }

    public Brand getUserChoice() {
        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(this);
    }

    public void addBrand() {
        boolean checkBrandID = false;
        do {
            System.out.print("Enter brand ID: ");
            brandID = sc.nextLine();
            for (Brand aThi : this) {
                if (brandID.equals(aThi.getBrandID())) {
                    checkBrandID = true;
                    System.out.println("This brand ID is existed. Try again!");
                    break;
                } else {
                    checkBrandID = false;
                }
            }
        } while (checkBrandID == true);
        do {
            System.out.print("Enter brand name: ");
            brandName = sc.nextLine();
            if (brandName.equals("") != true) {
                break;
            }
            System.out.println("Brand name can not blank. Try again!");
        } while (true);
        do {
            System.out.print("Enter sound brand: ");
            soundBrand = sc.nextLine();
            if (soundBrand.equals("") != true) {
                break;
            }
            System.out.println("Sound brand can not blank. Try again!");
        } while (true);
        do {
            System.out.print("Enter price: ");
            try {
                price = Double.parseDouble(sc.nextLine());
                if (price <= 0) {
                    System.out.println("The price can not blank. Try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("The price must be a number. Try again!");
                price = 0;
            }
        } while (price == 0);
        this.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.println("Brand has add successfully!");
    }

    public void updateBrand() {
        do {
            System.out.print("Enter brand ID: ");
            brandID = sc.nextLine();
            pos = searchID(brandID);
            if (pos != -1) {
                break;
            }
            System.out.println("Brand ID can not blank. Try again!");
        } while (true);

        do {
            System.out.print("Enter brand name: ");
            brandName = sc.nextLine();
            if (brandName.equals("") != true) {
                break;
            }
            System.out.println("Brand name can not blank. Try again!");
        } while (true);

        do {
            System.out.print("Enter sound brand: ");
            soundBrand = sc.nextLine();
            if (soundBrand.equals("") != true) {
                break;
            }
            System.out.println("Sound brand can not blank. Try again!");
        } while (true);

        do {
            System.out.print("Enter price: ");
            try {
                price = Double.parseDouble(sc.nextLine());
                if (price <= 0) {
                    System.out.println("The price can not blank. Try again!");
                    price = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("The price must be a number. Try again!");
                price = 0;
            }
        } while (price == 0);

        this.get(pos).setUpdatedBrand(brandName, soundBrand, price);
        System.out.println("Brand has update successfully!");
    }

    public void listBrands() {
        for (Brand aThi : this) {
            System.out.println(aThi);
        }
    }

}
