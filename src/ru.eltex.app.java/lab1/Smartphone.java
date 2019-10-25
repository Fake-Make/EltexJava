package ru.eltex.app.java.lab1;

import java.util.Arrays;

/**
 * Smartphone-class
 *
 * @author Dmitry Nevada
 * @version 1.25.09.19
 */
public class Smartphone extends Device {
    /** Array of available sim types */
    protected String[] simTypes = {"micro", "common"};
    /** Index of actual sim type in simTypes array */
    protected int simType;
    /** Actual amount of sim-cards */
    protected int simCount;

    /** Default constructor with empty fields */
    public Smartphone() {}

    /**
     * Overloaded constructor with tow main fields
     *
     * @param name
     * @param price
     * @throws Exception if price is lower than 0
     */
    public Smartphone(String name, double price) throws Exception {
        super(name, price);
    }

    /**
     * Overloaded constructor with all possible fields
     *
     * @param name
     * @param factory
     * @param model
     * @param os
     * @param price
     * @throws Exception if price is lower than 0
     */
    public Smartphone(String name, String factory, String model, String os, double price) throws Exception {
        super(name, factory, model, os, price);
    }

    /**
     * Overloaded constructor with all possible fields including Smartphone fields
     *
     * @param name
     * @param factory
     * @param model
     * @param os
     * @param price
     * @param simType
     * @param simCount
     * @throws Exception if price is lower than 0
     */
    public Smartphone(String name, String factory, String model, String os, double price, int simType, int simCount) throws Exception {
        super(name, factory, model, os, price);
        setSimCount(simCount);
        setSimType(simType);
    }

    public String[] getSimTypes() {
        return simTypes;
    }

    public void setSimTypes(String[] simTypes) {
        this.simTypes = simTypes;
    }

    /** Returns simType as a String */
    public String getSimType() {
        return simTypes[simType];
    }

    /**
     * Set sim-cards type
     *
     * @param simType Index of simType in simTypes array
     * @throws Exception if index is out of array range
     */
    public void setSimType(int simType) throws Exception {
        if (simType >= simTypes.length || simType < 0)
            throw new Exception("Such sim type is not available for smartphones!");
        this.simType = simType;
    }

    /**
     * Set sim-cards type
     *
     * @param simType SimType as a String value
     * @throws Exception if index is out of array range
     */
    public void setSimType(String simType) throws Exception {
        if (!Arrays.asList(simTypes).contains(simType))
            throw new Exception("Such sim type is not available for smartphones!");
        this.simType = Arrays.asList(simTypes).indexOf(simType);
    }

    public int getSimCount() {
        return simCount;
    }

    /**
     * Set sim-cards amount
     *
     * @param simCount Amount of sim-cards
     * @throws Exception if simCount is lower than 0
     */
    public void setSimCount(int simCount) throws Exception{
        if (0 > simCount)
            throw new Exception("Amount of sim-cards can't be negative value!");
        this.simCount = simCount;
    }
}