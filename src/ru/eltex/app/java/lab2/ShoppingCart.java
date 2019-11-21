package ru.eltex.app.java.lab2;

import ru.eltex.app.java.lab1.Device;

import java.util.LinkedList;

/**
 * ShoppingCart class
 *
 * @author Dmitry Nevada
 * @version 1.19.11.19
 */
public class ShoppingCart {
    /** Basic container for Device-typed objects */
    protected LinkedList<Device> cartList;

    /** Default constructor */
    public ShoppingCart() {
        cartList = new LinkedList<>();
    }

    /**
     * Adding new item to cart
     *
     * @param item Object of Device-type
     */
    public void add(Device item) {
        cartList.add(item);
    }

    /**
     * Removing item from cart
     *
     * @param item Object of Device-type
     */
    public int delete(Device item) {
        int index = cartList.indexOf(item);
        if (-1 == index)
            return 1;
        cartList.remove(index);
        return 0;
    }

    /** Show all items from cart */
    public void showAll() {
        for (Device item : cartList) {
            item.read();
        }
    }

    public LinkedList<Device> getCartList() {
        return cartList;
    }
}
