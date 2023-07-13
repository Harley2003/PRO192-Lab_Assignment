package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CarList extends ArrayList<Car> {

    private String carID, color, frameID, engineID;
    private Brand brand;
    Menu menu = new Menu();
    Scanner sc = new Scanner(System.in);
    BrandList brandList;
    BufferedReader br;
    String line;
    String[] arr;

    public CarList(BrandList bList) {
        brandList = bList;
    }

    public boolean loadFromFile(String fileName) throws IOException {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream("resource\\" + fileName);
            br = new BufferedReader(new InputStreamReader(inputStream));
            line = br.readLine();
            while (line != null) {
                arr = line.split(",");
                carID = arr[0].trim();
                brand = brandList.get(brandList.searchID(arr[1].trim()));
                color = arr[2].trim();
                frameID = arr[3].trim();
                engineID = arr[4].trim();
                this.add(new Car(carID, brand, color, frameID, engineID));
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
            try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
                for (Car i : this) {
                    pw.println(i);
                }
            }
            return true;
        } catch (IOException e) {
        }

        return false;
    }

    public int searchID(String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (carID.equals(this.get(i).getCarID())) {
                return i;
            } else {
            }
        }
        return -1;
    }

    private int searchEngineID(String searchEngineID) {
        for (int i = 0; i < this.size(); i++) {
            if (searchEngineID.equals(this.get(i).getEngineID())) {
                return i;
            }
        }
        return -1;
    }

    private int searchFrameID(String searchFrameID) {
        for (int i = 0; i < this.size(); i++) {
            if (searchFrameID.equals(this.get(i).getFrameID())) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        boolean checkCarID = false;
        do {
            System.out.print("Enter car ID: ");
            carID = sc.nextLine();
            for (Car aThi : this) {
                if (carID.equals(aThi.getCarID())) {
                    checkCarID = true;
                    System.out.println("This car ID is existed. Try again!");
                    break;
                } else {
                    checkCarID = false;
                }
            }
        } while (checkCarID == true);

        Brand brand = menu.ref_getChoice(brandList);

        do {
            System.out.print("Enter color: ");
            color = sc.nextLine();
            if (color.equals("") != true) {
                break;
            }
            System.out.println("The color can not blank. Try again!");
        } while (true);
        do {
            System.out.print("Enter frame ID: ");
            frameID = sc.nextLine();
            if ((frameID.matches("F[0-9][0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                break;
            }
            System.out.println("The frame ID must be in F00000 format and not be duplicated. Try again!");
        } while (true);
        do {
            System.out.print("Enter engine ID: ");
            engineID = sc.nextLine();
            if ((engineID.matches("E[0-9][0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                break;
            }
            System.out.println("The engine ID must be in E0000 format and not be duplicated. Try again!");
        } while (true);
        this.add(new Car(carID, brand, color, frameID, engineID));
        System.out.println("Car has add successfully!");
    }

    public void printBasedBrandName() throws IOException {
        System.out.print("Enter a part of the brand name: ");
        String partOfBrandName = sc.nextLine();
        int count = 0;
        for (Car i : this) {
            if (i.getBrand().getBrandName().contains(partOfBrandName)) {
                System.out.println(i.screenString());
                count++;
            }

            if (count == 0) {
                System.out.println("No result!");
                break;
            }
        }
    }

    public boolean removeCar() {
        int pos;
        String removedID;
        System.out.print("Enter car ID to remove: ");
        removedID = sc.nextLine();
        pos = searchID(removedID);
        if (pos >= 0) {
            this.remove(pos);
            return true;
        }
        return false;
    }

    public boolean updateCar() {
        int pos;
        String updatedID;
        System.out.print("Enter car ID to update: ");
        updatedID = sc.nextLine();
        pos = searchID(updatedID);
        if (pos >= 0) {
            Brand brand = menu.ref_getChoice(brandList);

            do {
                System.out.print("Enter color: ");
                color = sc.nextLine();
                if (color.equals("") != true) {
                    break;
                }
                System.out.println("The color can not blank. Try again!");
            } while (true);
            do {
                System.out.print("Enter frame ID: ");
                frameID = sc.nextLine();
                if ((frameID.matches("F[0-9][0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                    break;
                }
                System.out.println("The frame ID must be in F00000 format and not be duplicated. Try again!");
            } while (true);
            do {
                System.out.print("Enter engine ID: ");
                engineID = sc.nextLine();
                if ((engineID.matches("E[0-9][0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                    break;
                }
                System.out.println("The engine ID must be in E00000 format and not be duplicated. Try again!");
            } while (true);
            this.get(pos).setUpdatedCar(brand, color, frameID, engineID);
            return true;
        } else {
            System.out.println("Not found!");
        }
        return false;
    }

    public void listCars() {
        Collections.sort(this.subList(modCount, modCount));
        for (Car i : this) {
            System.out.println(i.toString());
        }
    }
}
