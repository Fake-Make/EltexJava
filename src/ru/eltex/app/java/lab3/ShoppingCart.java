package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab1.Device;

import java.io.Serializable;
import java.util.*;

/**
 * ShoppingCart class
 *
 * @author Dmitry Nevada
 * @version 1.23.11.19
 */
public class ShoppingCart<T extends Device> implements Serializable {
    /** Basic container for Device-typed objects */
    protected List<T> cartList;
    /** Additional container for device-items' IDs */
    protected Set<UUID> devicesIds;

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
    public void add(T item) {
        cartList.add(item);
        devicesIds.add(item.getId());
    }

    /**
     * Removing item from cart
     *
     * @param item Object of Device-type
     */
    public int delete(T item) {
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
        for (T item : cartList) {
            item.read();
        }
    }

    public T searchById(UUID id) {
        if (!devicesIds.contains(id))
            return null;
        for (T item : cartList) {
            if (id.equals(item.getId()))
                return item;
        }
        return null;
    }

    public List<T> getCartList() {
        return cartList;
    }
}
