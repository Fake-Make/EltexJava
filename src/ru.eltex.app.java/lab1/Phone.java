package ru.eltex.app.java.lab1;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Phone-class
 *
 * @author Dmitry Nevada
 * @version 1.25.10.19
 */
public class Phone extends Device {
    /** Array of available form types */
    protected String[] formTypes = {"classic", "flipper"};
    /** Index of actual form type in formTypes array */
    protected int formType;

    /** Simplest constructor */
    public Phone() {}

    /**
     * Overloaded constructor with two main fields
     *
     * @throws Exception if price is lower than 0
     */
    public Phone(String name, double price) throws Exception {
        super(name, price);
    }

    /**
     * Overloaded constructor with all possible fields from super-class
     *
     * @throws Exception if price is lower than 0
     */
    public Phone(String name, String factory, String model, String os, double price) throws Exception {
        super(name, factory, model, os, price);
    }

    /**
     * Overloaded constructor with all possible fields where formType is int
     *
     * @throws Exception if price is lower than 0
     */
    public Phone(String name, String factory, String model, String os, double price, int formType) throws Exception {
        super(name, factory, model, os, price);
        setFormType(formType);
    }

    /**
     * Overloaded constructor with all possible fields where formType is String
     *
     * @throws Exception if price is lower than 0
     */
    public Phone(String name, String factory, String model, String os, double price, String formType) throws Exception {
        super(name, factory, model, os, price);
        setFormType(formType);
    }

    /** @return Available form types as array */
    public String[] getFormTypes() {
        return formTypes;
    }

    /** @return Form type as index of formTypes array */
    public int getFormTypeIndex() {
        return formType;
    }

    /** @return Form type in String format */
    public String getFormType() {
        return formTypes[formType];
    }

    /**
     * Set available form types
     * @param formTypes array of available form types
     */
    public void setFormTypes(String[] formTypes) {
        this.formTypes = formTypes;
    }

    /**
     * Set actual formType as index of array
     * @param formType index of formTypes array for actual formType
     * @throws Exception if there is no such types in available types array
     */
    public void setFormType(int formType) throws Exception {
        if (formType >= formTypes.length || formType < 0)
            throw new Exception("Such form type is not available for phones!");
        this.formType = formType;
    }

    /**
     * Set actual formType as String
     * @param formType actual form type as String
     * @throws Exception if there is no such types in available types array
     */
    public void setFormType(String formType) throws Exception {
        if (!Arrays.asList(formTypes).contains(formType))
            throw new Exception("Such form type is not available for phones!");
        this.formType = Arrays.asList(formTypes).indexOf(formType);
    }

    @Override
    public void update() {
        super.update();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter device form type from available:");
        System.out.println(formTypes.toString());
        try {
            setFormType(scanner.nextLine());
        } catch (Exception e) {
            formType = 0;
            System.out.println("You wrote wrong form type, so now form type is " + formTypes[formType]);
        }
    }

    @Override
    public void read() {
        super.read();
        System.out.println("Device's form type: " + formTypes[formType]);
    }
}