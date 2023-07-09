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
    Scanner scanner = new Scanner(System.in);
    BrandList brandList;
    BufferedReader br;
    PrintWriter pw;
    String line;
    String[] arr;

    public CarList(BrandList bList) {
        brandList = bList;
    }

    public boolean loadFromFile(String fileName) throws IOException {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream("resource/" + fileName);
            br = new BufferedReader(new InputStreamReader(inputStream));
            line = br.readLine();
            while (line != null) {
                arr = line.split(",");
                if (arr.length >= 5) {
                    carID = arr[0].trim();
                    brand = brandList.get(brandList.searchID(arr[1].trim()));
                    color = arr[2].trim();
                    frameID = arr[3].trim();
                    engineID = arr[4].trim();
                    this.add(new Car(carID, brand, color, frameID, engineID));
                }
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
            for (Car car : this) {
                pw.println(car.toString());
            }
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int searchID(String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (carID.equals(this.get(i).getCarID())) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        for (int i = 0; i < this.size(); i++) {
            if (fID.equals(this.get(i).getFrameID())) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEngineID().equals(eID)) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        boolean checkCarID = false;
        do {
            System.out.print("Enter car ID: ");
            carID = scanner.nextLine();
            for (int i = 0; i < this.size(); i++) {
                if (carID.equals(this.get(i).getCarID())) {
                    checkCarID = true;
                    System.out.println("This car ID is existed. Try another one!");
                    break;
                } else {
                    checkCarID = false;
                }
            }
        } while (checkCarID == true);

        brand = menu.ref_getChoice(brandList);

        do {
            System.out.print("Enter color: ");
            color = scanner.nextLine();
            if (color.equals("") != true) {
                break;
            }
            System.out.println("The color must not be null. Try again!");
        } while (true);
        do {
            System.out.print("Enter frame ID: ");
            frameID = scanner.nextLine();
            if ((frameID.matches("F[0-9][0-9][0-9][0-9][0-9]")) && (searchFrame(frameID) == -1)) {
                break;
            }
            System.out.println("The frame ID must be in F00000 format and not be duplicated. Try again!");
        } while (true);
        do {
            System.out.print("Enter engine ID: ");
            engineID = scanner.nextLine();
            if ((engineID.matches("E[0-9][0-9][0-9][0-9][0-9]")) && (searchFrame(engineID) == -1)) {
                break;
            }
            System.out.println("The engine ID must be in E00000 format and not be duplicated. Try again!");
        } while (true);
        this.add(new Car(carID, brand, color, frameID, engineID));
        System.out.println("Car has added successfully!");
    }

    public void printBasedBrandName() {
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a part of Brand Name: ");
        String partOfBrandName;
        try {
            partOfBrandName = br.readLine().trim();
            int count = 0;
            for (Car car : this) {
                if (car.getBrand().getBrandName().contains(partOfBrandName)) {
                    System.out.println(car.screenString());
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("No result!");
            }
        } catch (IOException e) {
            System.out.println("Error reading input!");
        }
    }

    public boolean removeCar() {
        int pos;
        String removedID;
        System.out.print("Enter car ID to removed: ");
        removedID = scanner.nextLine();
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
        System.out.print("Enter car ID to updated: ");
        updatedID = scanner.nextLine();
        pos = searchID(updatedID);
        if (pos >= 0) {
            brand = menu.ref_getChoice(brandList);

            do {
                System.out.print("Enter color: ");
                color = scanner.nextLine();
                if (color.equals("") != true) {
                    break;
                }
                System.out.println("The color must not be null. Try again!");
            } while (true);
            do {
                System.out.print("Enter frame ID: ");
                frameID = scanner.nextLine();
                if ((frameID.matches("F[0-9][0-9][0-9][0-9][0-9]")) && (searchFrame(frameID) == -1)) {
                    break;
                }
                System.out.println("The frame ID must be in F00000 format and not be duplicated. Try again!");
            } while (true);
            do {
                System.out.print("Enter engine ID: ");
                engineID = scanner.nextLine();
                if ((engineID.matches("E[0-9][0-9][0-9][0-9][0-9]")) && (searchEngine(engineID) == -1)) {
                    break;
                }
                System.out.println("The engine ID must be in E00000 format and not be duplicated. Try again!");
            } while (true);
            this.get(pos).setUpdatedCar(brand, color, frameID, engineID);
            return true;
        } else {
            System.out.println("Car not found!");
        }
        return false;
    }

    public void listCars() {
        Collections.sort(this.subList(modCount, modCount));
        for (Car car : this) {
            System.out.println(car.screenString());
        }
    }
}
