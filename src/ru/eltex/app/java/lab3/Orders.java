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
