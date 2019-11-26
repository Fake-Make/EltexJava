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
public abstract class ACheck {
    /**
     * Checks orders' status and change it
     *
     * @param purchase required order to check status
     * @return changed Order object
     */

    Orders<Order> ordersList;

    public ACheck() {
        ordersList = new Orders<>();
    }

    /**
     * Process all collection of orders
     * @return amount of processed orders
     */
    public abstract int process();
}
