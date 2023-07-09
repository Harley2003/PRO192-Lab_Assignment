package classes;

import com.BrandList;
import com.CarList;
import com.Menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CarManager {

    public static void main(String[] args) throws IOException, Exception {

        BrandList brandList = new BrandList();
        brandList.loadFromFile("brands.txt");

        CarList carList = new CarList(brandList);
        carList.loadFromFile("cars.txt");

        ArrayList<String> ops = new ArrayList<>();
        ops.add("List all brands");
        ops.add("Add a new brand");
        ops.add("Search a brand based on it's ID");
        ops.add("Update a brand");
        ops.add("Save brands to the file, named brands.txt");
        ops.add("List all cars in ascending order of brand names");
        ops.add("List cars based on a part of an input brand name");
        ops.add("Add a car");
        ops.add("Remove a car based on it's ID");
        ops.add("Update a car based on it's ID");
        ops.add("Save cars to file, named cars.txt");

        int choice;
        boolean checkSuccessful;
        Menu menu = new Menu();
        do {
            choice = menu.int_getChoice(ops);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.print("Enter Brand ID to search: ");
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        String brandID = br.readLine().trim();
                        int pos = brandList.searchID(brandID);
                        if (pos == -1) {
                            System.out.println("Brand not found!");
                        } else {
                            System.out.println(brandList.get(pos));
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading input!");
                    }
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("brands.txt");
                    System.out.println("Brands saved to file successfully!");
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    checkSuccessful = carList.removeCar();
                    if (checkSuccessful) {
                        System.out.println("Car removed successfully!");
                    } else {
                        System.out.println("Car not found!");
                    }
                    break;
                case 10:
                    checkSuccessful = carList.updateCar();
                    if (checkSuccessful) {
                        System.out.println("Car updated successfully!");
                    }
                    break;
                case 11:
                    carList.saveToFile("cars.txt");
                    System.out.println("Cars saved to file successfully!");
                    break;
            }
        } while (choice > 0 && choice <= ops.size());
    }
}
