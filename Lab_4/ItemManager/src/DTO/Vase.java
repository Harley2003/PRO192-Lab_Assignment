package DTO;

import java.util.Scanner;

public class Vase extends Item {

    private int height;
    private String material;

    public Vase() {
    }

    public Vase(int value, String creator, int height, String material) {
        super(value, creator);
        this.height = height;
        this.material = material;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void outputVase() {
        super.output();
        System.out.println("Height: " + this.height);
        System.out.println("Material: " + this.material);
    }

    public void inputVase() {
        Scanner sc = new Scanner(System.in);
        super.input();

            System.out.println("Enter height: ");
            this.height = sc.nextInt();
            while (this.height < 0) {
                this.height = sc.nextInt();
            }
            System.out.println("Enter material: ");
            this.material = sc.nextLine();
            while (material.isEmpty()) {
            this.material = sc.nextLine();
        }
        

    }
}
