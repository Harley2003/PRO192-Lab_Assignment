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
    Scanner scanner = new Scanner(System.in);
    PrintWriter pw;
    BufferedReader br;

    public BrandList() {
    }

    public boolean loadFromFile(String fileName) throws IOException {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream("resource\\" + fileName);
            br = new BufferedReader(new InputStreamReader(inputStream));
            String[] arr;
            String line = br.readLine();
            while ((line != null)) {
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

    public boolean saveToFile(String fileName) {
        try {
            pw = new PrintWriter(new FileWriter(fileName));
            for (Brand i : this) {
                pw.println(i);
            }
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int searchID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equals(ID)) {
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
            System.out.print("Input brand ID: ");
            brandID = scanner.nextLine();
            for (int i = 0; i < this.size(); i++) {
                if (brandID.equals(this.get(i).getBrandID())) {
                    checkBrandID = true;
                    System.out.println("This brand ID is existed. Try another one!");
                    break;
                } else {
                    checkBrandID = false;
                }
            }
        } while (checkBrandID == true);
        do {
            System.out.print("Enter brand name: ");
            brandName = scanner.nextLine();
            if (brandName.equals("") != true) {
                break;
            }
            System.out.println("The brand name must not be null. Try again!");
        } while (true);
        do {
            System.out.print("Enter sound brand: ");
            soundBrand = scanner.nextLine();
            if (soundBrand.equals("") != true) {
                break;
            }
            System.out.println("The sound brand must not be null. Try again!");
        } while (true);
        do {
            System.out.print("Enter price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.out.println("The price must not be null. Try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("The price must be a number. Try again!");
                price = 0;
            }
        } while (price == 0);
        this.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.println("Brand has added successfully");
    }

    public void updateBrand() {
        br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter Brand ID to update: ");
            brandID = br.readLine().trim();

            pos = searchID(brandID);
            if (pos == -1) {
                System.out.println("Brand not found!");
                return;
            }

            Brand brand = this.get(pos);

            System.out.print("Enter new Brand Name: ");
            brandName = br.readLine().trim();
            brand.setBrandName(brandName);

            System.out.print("Enter new Sound Brand: ");
            soundBrand = br.readLine().trim();
            brand.setSoundBrand(soundBrand);

            do {
                System.out.print("Enter new Price: ");
                price = Double.parseDouble(br.readLine().trim());
            } while (price <= 0);
            brand.setPrice(price);

            System.out.println("Brand updated successfully!");
        } catch (IOException e) {
            System.out.println("Error reading input!");
        }
    }

    public void listBrands() {
        for (Brand brand : this) {
            System.out.println(brand);
        }
    }
}
