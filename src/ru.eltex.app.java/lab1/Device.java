package ru.eltex.app.java.lab1;
import java.util.Scanner;
import java.util.UUID;

/**
 * Abstract Device class
 *
 * @author Dmitry Nevada
 * @version 1.20.09.19
 */
public abstract class Device implements ICrudAction {
    /**
     * Device Name,
     * Device Producer,
     * Device Model,
     * Device Operation System
     */
    protected String name, factory, model, os;
    /** Device base price */
    protected double price;
    /** Device ID */
    protected UUID id;
    /** Amount of defined devices */
    protected static int count;
    /** Flag to check if this object was deleted by its own method */
    protected boolean isDeleted = true;

    /** Default constructor with empty fields */
    public Device() {
        id = UUID.randomUUID();
    }

    /**
     * Overloaded constructor with tow main fields
     *
     * @throws Exception if price is lower than 0
     */
    public Device(String name, double price) throws Exception {
        if (price < 0)
            throw new Exception("Device price can't be negative!");
        this.name = name;
        this.price = price;
        id = UUID.randomUUID();
        switchDeletedFlag(false);
    }

    /**
     * Overloaded constructor with all possible fields
     *
     * @throws Exception if price is lower than 0
     */
    public Device(String name, String factory, String model, String os, double price) throws Exception {
        if (price < 0)
            throw new Exception("Device price can't be negative!");
        this.name = name;
        this.factory = factory;
        this.model = model;
        this.os = os;
        this.price = price > 0 ? price : 0;
        id = UUID.randomUUID();
        switchDeletedFlag(false);
    }

    public void setName(String name) {
        this.name = name;
        switchDeletedFlag(false);
    }

    public void setFactory(String factory) {
        this.factory = factory;
        switchDeletedFlag(false);
    }

    public void setModel(String model) {
        this.model = model;
        switchDeletedFlag(false);
    }

    public void setOs(String os) {
        this.os = os;
        switchDeletedFlag(false);
    }

    public boolean setPrice(double price) {
        if (price < 0)
            return false;

        this.price = price;
        switchDeletedFlag(false);
        return true;
    }

    public String getName() {
        return name;
    }

    public String getFactory() {
        return factory;
    }

    public String getModel() {
        return model;
    }

    public String getOs() {
        return os;
    }

    public double getPrice() {
        return price;
    }

    public UUID getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    /**
     * Switches isDeleted flag into intoStatement and change count of all devices if it's needed
     *
     * @param intoStatement required statement of isDeleted flag
     */
    private void switchDeletedFlag(boolean intoStatement) {
        if (!intoStatement && isDeleted) {
            isDeleted = false;
            count++;
            return;
        }
        if (intoStatement && !isDeleted) {
            isDeleted = true;
            count--;
        }
    }

    /**
     * Random generation of object, increasing counter value
     */
    public void create() {
        this.delete();
        this.name = "Sample name";
        this.factory = "Sample factory";
        this.model = "Sample model";
        this.os = "Sample OS";
        this.price = 50.;
        switchDeletedFlag(false);
    };

    /**
     * Data output
     */
    public void read() {
        if (isDeleted) {
            System.out.println("This device was deleted or not created");
            return;
        }
        System.out.println(
                null == name ? "Device name is not defined;" : "Device name: " + name
        );
        System.out.println(
                null == model ? "Device model is not defined;" : "Device model: " + model
        );
        System.out.println(
                null == factory ? "Device manufacturer is not defined;" : "Device manufacturer: " + factory
        );
        System.out.println(
                null == os ? "Device OS is not defined;" : "Device OS: " + os
        );
        System.out.println(
                0.0 == price ? "Device is free or price is not defined;" : "Device price: " + price
        );
        System.out.println(
                "Device ID: " + id
        );
        System.out.println(
                0 == count ? "There is no devices;" :
                        "There is " + count + " device" + (1 == count ? ";" : "s;")
        );
    };

    /**
     * Manual input of data
     */
    public void update() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter device name:");
        name = scanner.nextLine();
        System.out.println("Please enter device model:");
        model = scanner.nextLine();
        System.out.println("Please enter device manufacturer:");
        factory = scanner.nextLine();
        System.out.println("Please enter device OS:");
        os = scanner.nextLine();
        System.out.println("Please enter device price:");
        price = Double.parseDouble(scanner.nextLine());

        if (0 > price)
            price = 0.0;

        switchDeletedFlag(false);
    };

    /**
     * Forced reset of data, decreasing counter value
     */
    public void delete() {
        name = factory = model = os = null;
        price = 0.0;
        switchDeletedFlag(true);
    };
}