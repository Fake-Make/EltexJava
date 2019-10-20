package ru.eltex.app.java.lab1;

import java.util.Arrays;

/**
 * Phone-class
 *
 * @author Dmitry Nevada
 * @version 0.17.09.19
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
     * Overloaded constructor with all possible fields
     *
     * @throws Exception if price is lower than 0
     */
    public Phone(String name, String factory, String model, String os, double price, int formType) throws Exception {
        super(name, factory, model, os, price);
        this.formType = formType;
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
     */
    public void setFormType(int formType) {
        this.formType = formType;
    }

    /**
     * Set actual formType as String
     * @param formType actual form type as String
     * @throws Exception if there is no such types in available types array
     */
    public void setFormType(String formType)  throws Exception {
        if (!Arrays.asList(formTypes).contains(formType))
            throw new Exception("Such form type is not available for phones!");
        this.formType = Arrays.asList(formTypes).indexOf(formType);
    }
}