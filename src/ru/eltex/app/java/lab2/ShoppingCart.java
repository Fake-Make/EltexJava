package ru.eltex.app.java.lab2;

import ru.eltex.app.java.lab1.Device;

import java.util.LinkedList;

/**
 * ShoppingCart class
 *
 * @author Dmitry Nevada
 * @version 0.11.19.19
 */
public class ShoppingCart {
    /** Basic container for Device-typed objects */
    protected LinkedList<Device> cart;

    /** Default constructor */
    public ShoppingCart() {
        cart = new LinkedList<>();
    }

    /**
     * Adding new item to cart
     *
     * @param item Object of Device-type
     */
    public void add(Device item) {
        cart.add(item);
    }

    /**
     * Removing item from cart
     *
     * @param item Object of Device-type
     */
    public int delete(Device item) {
        int index = cart.indexOf(item);
        if (-1 == index)
            return 1;
        cart.remove(index);
        return 0;
    }
}
