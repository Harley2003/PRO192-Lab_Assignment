package DTO;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Painting extends Item {

    private int height;
    private int width;
    private boolean isWatercolour;
    private boolean isFramed;

    public Painting() {
    }

    public Painting(int value, String creator, int height, int width, boolean isWatercolour, boolean isFramed) {
        super(value, creator);
        this.height = height;
        this.width = width;
        this.isWatercolour = isWatercolour;
        this.isFramed = isFramed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isIsWatercolour() {
        return isWatercolour;
    }

    public void setIsWatercolour(boolean isWatercolour) {
        this.isWatercolour = isWatercolour;
    }

    public boolean isIsFramed() {
        return isFramed;
    }

    public void setIsFramed(boolean isFramed) {
        this.isFramed = isFramed;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void outputPaiting() {
        super.output();
        System.out.println("Height: " + this.height);
        System.out.println("Width: " + this.width);
        System.out.println("isWatercolour: " + this.isWatercolour);
        System.out.println("isFramed: " + this.isFramed);
    }

    public void inputPaiting() {
        super.input();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter height: ");
        this.height = sc.nextInt();
        while (this.height < 0) {
            this.height = sc.nextInt();
        }
        System.out.println("Enter width: ");
        this.width = sc.nextInt();
        while (this.width < 0) {
            this.width = sc.nextInt();
        }
        
        System.out.println("Enter isWatercolour: ");
        this.isWatercolour = sc.nextBoolean();
        System.out.println("Enter isFramed: ");
        this.isFramed = sc.nextBoolean();
    }

}
