package ru.eltex.app.java.lab2;

import ru.eltex.app.java.lab1.Device;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.UUID;

/**
 * ShoppingCart class
 *
 * @author Dmitry Nevada
 * @version 1.19.11.19
 */
public class ShoppingCart {
    /** Basic container for Device-typed objects */
    protected LinkedList<Device> cartList;
    /** Additional container for device-items' IDs */
    protected HashSet<UUID> devicesIds;

    /** Default constructor */
    public ShoppingCart() {
        cartList = new LinkedList<>();
        devicesIds = new HashSet<>();
    }

    /**
     * Adding new item to cart
     *
     * @param item Object of Device-type
     */
    public void add(Device item) {
        cartList.add(item);
        devicesIds.add(item.getId());
    }

    /**
     * Removing item from cart
     *
     * @param item Object of Device-type
     */
    public int delete(Device item) {
        /** TODO: Rebuild method using HashSet */
        int index = cartList.indexOf(item);
        if (-1 == index)
            return 1;
        cartList.remove(index);
        devicesIds.remove(item.getId());
        return 0;
    }

    /** Show all items from cart */
    public void showAll() {
        for (Device item : cartList) {
            item.read();
        }
    }

    public Device searchById(UUID id) {
        if (!devicesIds.contains(id))
            return null;
        for (Device item : cartList) {
            if (id == item.getId())
                return item;
        }
        return null;
    }

    public LinkedList<Device> getCartList() {
        return cartList;
    }
}
