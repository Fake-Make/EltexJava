package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab2.OrderStatus;

import java.io.Serializable;
import java.util.*;

/**
 * ShoppingCart class
 *
 * @author Dmitry Nevada
 * @version 1.19.11.19
 */
public class Orders<T extends Order> implements Serializable {
    /** Basic container for Order-typed objects */
    protected List<T> ordersList;
    /** Secondary container for Order-typed objects by their creating time */
    protected Map<Calendar, T> ordersListByCreateTime;

    /** Default constructor */
    public Orders() {
        ordersList = new ArrayList<>();
        ordersListByCreateTime = new TreeMap<>();
    }

    /**
     * Adding new item to orders list
     *
     * @param order Object of Order-type
     */
    public boolean add(T order) {
        if(contains(order.getId()))
            return false;
        ordersList.add(order);
        ordersListByCreateTime.put(order.getCreateTime(), order);
        return true;
    }

    public void replace(T order) {
        if (contains(order.getId()))
            remove(searchById(order.getId()));
        add(order);
    }

    public List<T> getOrdersList() {
        return ordersList;
    }

    /**
     * Removing item from orders list
     *
     * @param order Object of Order-type
     */
    public int remove(T order) {
        ordersListByCreateTime.remove(order.getCreateTime());
        int index = ordersList.indexOf(order);
        if (-1 == index)
            return 1;
        ordersList.remove(index);
        return 0;
    }

    /**
     * Search T in orderList<T> by its id
     * @param id for searching by
     * @return T or NULL
     */
    public T searchById(UUID id) {
        for (T item : ordersList) {
            if (id.equals(item.getId()))
                return item;
        }
        return null;
    }

    /**
     * Check if orderList<T> contains an item by its id
     * @param id for searching by
     * @return true if T with id is in orderList, false otherwise
     */
    public boolean contains(UUID id) {
        for (T item : ordersList) {
            if (id.equals(item.getId()))
                return true;
        }
        return false;
    }

    /**
     * Remove T from orderList by its id
     * @param id for searching by
     * @return true if item was found and removed, false otherwise
     */
    public boolean removeById(UUID id) {
        return 0 == remove(searchById(id)) ? true : false;
    }

    /**
     * Creating order and pushing it to container
     *
     * @param cart ShoppingCart-typed object for creating order
     * @param person Credentials-typed object for creating order
     */
    public void makePurchase(ShoppingCart cart, Credentials person) {
        this.add((T) T.makePurchase(cart, person));
    }

    /**
     * Removing all processed orders with expired awaiting time
     *
     * @returns amount of removed orders
     */
    public int removeExpiredElements(boolean checkTime) {
        List<T> toRemove = new ArrayList<>();
        for (T order : ordersList) {
            /** expired and processed OR just processed regarding checkTime-flag */
            if (checkTime && order.isExpired() && order.isProcessed() || !checkTime && order.isProcessed()) {
                toRemove.add(order);
            }
        }
        for (T order : toRemove) {
            this.remove(order);
        }
        return toRemove.size();
    }

    public int processElements() {
        int wasProcessed = 0;
        for (int i = 0; i < ordersList.size(); i++) {
            if (OrderStatus.AWAITING == ordersList.get(i).getStatus()) {
                ordersList.get(i).setStatus(OrderStatus.PROCESSED);
                wasProcessed++;
            }
        }

        return wasProcessed;
    }

    /** Show all orders and their information */
    public void showAllOrders() {
        for (T order : ordersList) {
            order.read();
            System.out.println();
        }
        System.out.println();
    }

    public int getOrdersAmount() {
        return ordersList.size();
    }
}
