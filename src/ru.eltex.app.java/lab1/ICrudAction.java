package ru.eltex.app.java.lab1;

/**
 * CRUD-interface for Eltex programming school
 *
 * @author Dmitry Nevada
 * @version 0.17.09.19
 */
public interface ICrudAction {
    /**
     * Random generation of object, increasing counter value
     */
    public void create();

    /**
     * Data output
     */
    public void read();

    /**
     * Manual input of data
     */
    public void update();

    /**
     * Forced reset of data, decreasing counter value
     */
    public void delete();
}