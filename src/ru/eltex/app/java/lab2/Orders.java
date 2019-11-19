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
    protected ArrayList<Order> ordersList;

    /** Default constructor */
    public Orders() {
        ordersList = new ArrayList<>();
    }

    /**
     * Adding new item to orders list
     *
     * @param order Object of Device-type
     */
    public void add(Order order) {
        ordersList.add(order);
    }

    /**
     * Removing item from orders list
     *
     * @param order Object of Device-type
     */
    public int remove(Order order) {
        int index = ordersList.indexOf(order);
        if (-1 == index)
            return 1;
        ordersList.remove(index);
        return 0;
    }
}
