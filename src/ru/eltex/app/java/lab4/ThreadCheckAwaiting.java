package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadCheckAwaiting extends ACheck {
    protected int runTimeout = 8, runTimeoutRange = runTimeout / 4;

    public ThreadCheckAwaiting(Orders<Order> orderList) {
        super(orderList);
    }

    @Override
    public int process() {
        return ordersList.processElements();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ordersList) {
                this.process();
            }
            try {
                sleep(100 * ThreadLocalRandom.current().nextInt(runTimeout - runTimeoutRange, runTimeout + runTimeoutRange));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
