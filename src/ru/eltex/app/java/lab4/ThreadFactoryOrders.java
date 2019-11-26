package ru.eltex.app.java.lab4;

import ru.eltex.app.java.factories.AFactory;
import ru.eltex.app.java.factories.FactoryOrders;
import ru.eltex.app.java.lab3.Order;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadFactoryOrders extends Thread {
    protected int runTimeout = 4, runTimeoutRange = runTimeout / 4;

    public ThreadFactoryOrders() {
        super();
    }

    public ThreadFactoryOrders(int runTimeout) {
        super();
        this.runTimeout = runTimeout / 100;
    }

    @Override
    public void run() {
        AFactory<Order> factoryOrders = new FactoryOrders<>();

        while (true) {
            /** Where to put created orders? */
            factoryOrders.produce();
            try {
                sleep(100 * ThreadLocalRandom.current().nextInt(runTimeout - runTimeoutRange, runTimeout + runTimeoutRange));
            } catch (InterruptedException e) {
                System.out.println("InterruptedException occurred at ThreadFactoryOrders: " + e);
            }
        }
    }
}
