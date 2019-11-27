package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

/**
 * ACheck class
 * Check and change orders' status
 *
 * @author Dmitry Nevada
 * @version 0.26.11.19
 */
public abstract class ACheck extends Thread {
    protected Orders<Order> ordersList;

    public ACheck(Orders<Order> ordersList) {
        this.ordersList = ordersList;
    }

    /**
     * Process all collection of orders
     * @return amount of processed orders
     */
    public abstract int process();
}
