package ru.eltex.app.java.lab1;

/**
 * Smartphone-class
 *
 * @author Dmitry Nevada
 * @version 1.26.10.19
 */
public class Tablet extends Device {
    /** Video chip */
    protected String videoChip;
    /**
     * Screen resolution
     *
     * @todo Develop a structure with two fields: width and height in pixels
     */
    protected String screenResolution;

    /** Default constructor with empty fields */
    public Tablet() {}

    /**
     * Overloaded constructor with tow main fields
     *
     * @param name
     * @param price
     * @throws Exception if price is lower than 0
     */
    public Tablet(String name, double price) throws Exception {
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
    public Tablet(String name, String factory, String model, String os, double price) throws Exception {
        super(name, factory, model, os, price);
    }

    /**
     * Overloaded constructor with all possible fields including Tablet fields
     *
     * @param name
     * @param factory
     * @param model
     * @param os
     * @param price
     * @param videoChip
     * @param screenResolution
     * @throws Exception if price is lower than 0
     */
    public Tablet(String name, String factory, String model, String os, double price, String videoChip, String screenResolution) throws Exception {
        super(name, factory, model, os, price);
        this.videoChip = videoChip;
        this.screenResolution = screenResolution;
    }

    public String getVideoChip() {
        return videoChip;
    }

    public void setVideoChip(String videoChip) {
        this.videoChip = videoChip;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }
}