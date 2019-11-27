package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

public class Main {
    public static void main(String[] args) {
        Orders<Order> ordersCollection = new Orders<>();

        /** Creating and starting factory without param */
        Thread threadOrdersFactory1 = new ThreadFactoryOrders(ordersCollection);
        threadOrdersFactory1.start();

        /** Creating and starting factory with param */
        Thread threadOrdersFactory2 = new ThreadFactoryOrders(ordersCollection, 200);
        threadOrdersFactory2.start();

        /** Creating and starting awaiting checker */
        Thread threadOrdersAwaitingChecker = new ThreadCheckAwaiting(ordersCollection);
        threadOrdersAwaitingChecker.start();

        /** Creating and starting processed checker */
        Thread threadOrdersProcessedChecker = new ThreadCheckProcessed(ordersCollection);
         threadOrdersProcessedChecker.start();

        while (true) {
            try {
                Thread.sleep(1000);

                synchronized (ordersCollection) {
                    System.out.println(ordersCollection.getOrdersAmount() + " orders;");
                    //ordersCollection.showAllOrders();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
