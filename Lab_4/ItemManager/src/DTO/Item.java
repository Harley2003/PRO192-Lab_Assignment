package DTO;

import java.util.Scanner;

public class Item {

    protected int value;
    protected String creator;

    public Item() {
    }

    public Item(int value, String creator) {
        this.value = value;
        this.creator = creator;
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

    public void output() {
        System.out.println("Creator: " + this.value);
        System.out.println("Value: " + this.creator);
    }

    public void input() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter value: ");
        this.value = sc.nextInt();
        while (this.value < 0) {
            this.value = sc.nextInt();
        }

        System.out.println("Enter creator: ");
        this.creator = sc.nextLine();
        while (creator.isEmpty()) {
            this.creator = sc.nextLine();
        }
    }
}
