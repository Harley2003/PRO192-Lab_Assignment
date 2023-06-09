package DTO;

import java.util.Scanner;

public class Statue extends Item {

    private int weight;
    private String colour;

    public Statue() {
    }

    public Statue(int value, String creator, int weight, String colour) {
        super(value, creator);
        this.weight = weight;
        this.colour = colour;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void outputStatue() {
        super.output();
        System.out.println("Weight: " + this.weight);
        System.out.println("Colour: " + this.colour);
    }

    public void inputStatue() {
        super.input();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter weight: ");
        this.weight = sc.nextInt();
        while (this.weight < 0) {
            this.weight = sc.nextInt();
        }
        System.out.println("Enter colour: ");
        this.colour = sc.nextLine();
        while (colour.isEmpty()) {
            this.colour = sc.nextLine();
        }
    }

}
