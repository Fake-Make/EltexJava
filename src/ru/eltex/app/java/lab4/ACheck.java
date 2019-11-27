package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.concurrent.ThreadLocalRandom;

/**
 * ACheck class
 * Check and change orders' status
 *
 * @author Dmitry Nevada
 * @version 1.27.11.19
 */
public abstract class ACheck extends Thread {
    protected long runTimeout = 2000, runTimeoutRange = 500;
    protected Orders<Order> ordersList;

    public ACheck(Orders<Order> ordersList) {
        this.ordersList = ordersList;
    }

    /**
     * Process all collection of orders
     * @return amount of processed orders
     */
    public abstract int process();

    @Override
    public void run() {
        while (true) {
            synchronized (ordersList) {
                process();
            }
            try {
                sleep(ThreadLocalRandom.current().nextLong(runTimeout - runTimeoutRange, runTimeout + runTimeoutRange));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
