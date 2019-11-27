package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

public class Main {
    public static void main(String[] args) {
        Orders<Order> ordersCollection = new Orders<>();

        /** Creating and starting factories */
        Thread threadOrdersFactory1 = new ThreadFactoryOrders(ordersCollection);
        Thread threadOrdersFactory2 = new ThreadFactoryOrders(ordersCollection, 2000);
        threadOrdersFactory1.start();
        threadOrdersFactory2.start();

        /** Creating and starting awaiting checker */
        Thread threadOrdersAwaitingChecker = new ThreadCheckAwaiting(ordersCollection);
        threadOrdersAwaitingChecker.start();

        /** Creating and starting processed checker */
        Thread threadOrdersProcessedChecker = new ThreadCheckProcessed(ordersCollection);
         threadOrdersProcessedChecker.start();

        System.out.println("Orders amount:");
        while (true) {
            try {
                Thread.sleep(100);

                synchronized (ordersCollection) {
                    System.out.print(ordersCollection.getOrdersAmount() + " ");
                    //ordersCollection.showAllOrders();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
