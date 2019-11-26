package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab2.OrderStatus;

import java.util.*;

/**
 * ShoppingCart class
 *
 * @author Dmitry Nevada
 * @version 1.19.11.19
 */
public class Orders<T extends Order> {
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
    public void add(T order) {
        ordersList.add(order);
        ordersListByCreateTime.put(order.getCreateTime(), order);
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
    public int removeExpiredElements() {
        int wasRemoved = 0;
        for (T order : ordersList) {
            if (order.isExpired() && order.isProcessed()) {
                this.remove(order);
                wasRemoved++;
            }
        }
        return wasRemoved;
    }

    public int processElements() {
        int wasProcessed = 0;
        for (T order : ordersList) {
            if (order.getStatus() == OrderStatus.AWAITING) {
                /** We'll remove real order and then add new order with changed status */
                remove(order);
                order.setStatus(OrderStatus.PROCESSED);
                add(order);
                wasProcessed++;
            }
        }
        return wasProcessed;
    }

    /** Show all orders and their information */
    public void showAllOrders() {
        for (T order : ordersList) {
            order.read();
        }
    }
}
