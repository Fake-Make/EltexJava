package ru.eltex.app.java.lab4;

import ru.eltex.app.java.factories.AFactory;
import ru.eltex.app.java.factories.FactoryOrders;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadFactoryOrders extends Thread {
    Orders<Order> ordersCollection;
    protected int runTimeout = 4, runTimeoutRange = runTimeout / 4;

    public ThreadFactoryOrders(Orders<Order> ordersCollection) {
        super();
        this.ordersCollection = ordersCollection;
    }

    public ThreadFactoryOrders(Orders<Order> ordersCollection, int runTimeout) {
        super();
        this.ordersCollection = ordersCollection;
        this.runTimeout = runTimeout / 100;
        runTimeoutRange = this.runTimeout / 4;
    }

    @Override
    public void run() {
        AFactory<Order> factoryOrders = new FactoryOrders<>();

        while (true) {
            /** Where to put created orders? */
            synchronized (ordersCollection) {
                ordersCollection.add(factoryOrders.produce());
            }

            try {
                sleep(100 * ThreadLocalRandom.current().nextInt(runTimeout - runTimeoutRange, runTimeout + runTimeoutRange));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
