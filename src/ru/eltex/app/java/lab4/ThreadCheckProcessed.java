package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadCheckProcessed extends ACheck {
    protected int runTimeout = 12, runTimeoutRange = runTimeout / 4;

    public ThreadCheckProcessed(Orders<Order> orderList) {
        super(orderList);
    }

    @Override
    public int process() {
        return ordersList.removeExpiredElements(false);
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
