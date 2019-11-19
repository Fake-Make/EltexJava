package ru.eltex.app.java.lab2;

import ru.eltex.app.java.lab1.Device;

import java.util.ArrayList;

/**
 * ShoppingCart class
 *
 * @author Dmitry Nevada
 * @version 1.19.11.19
 */
public class Orders {
    /** Basic container for Device-typed objects */
    protected ArrayList<Device> ordersList;

    /** Default constructor */
    public Orders() {
        ordersList = new ArrayList<>();
    }

    /**
     * Adding new item to orders list
     *
     * @param item Object of Device-type
     */
    public void add(Device item) {
        ordersList.add(item);
    }

    /**
     * Removing item from orders list
     *
     * @param item Object of Device-type
     */
    public int remove(Device item) {
        int index = ordersList.indexOf(item);
        if (-1 == index)
            return 1;
        ordersList.remove(index);
        return 0;
    }
}
