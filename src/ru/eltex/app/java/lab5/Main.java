package ru.eltex.app.java.lab5;

import ru.eltex.app.java.factories.FactoryOrders;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        boolean inJson = 0 < args.length && !args[0].isEmpty();

        String file = inJson ? "jsonFile" : "commonFile";

        try {
            Files.deleteIfExists(Path.of(file));
        } catch (Exception e) {
            // whatever
        }

        /** Init variables */
        Orders<Order> ordersCollection = new Orders<>();
        FactoryOrders<Order> factoryOrders = new FactoryOrders<>();

        /** Init managers */
        AManageOrder fileHandler = inJson ?
                new ManagerOrderJson(ordersCollection, file) :
                new ManagerOrderFile(ordersCollection, file);

        /** Produce orders */
        ordersCollection.add(factoryOrders.produce());
        ordersCollection.add(factoryOrders.produce());

        /** See collection */
        ordersCollection.showAllOrders();

        /** Save collection to file */
        fileHandler.saveAll();

        /** Add new element */
        Order tmp = factoryOrders.produce();
        ordersCollection.add(tmp);
        UUID id = tmp.getId();
        System.out.println("Added order's ID:" + id);

        /** Save it to file */
        fileHandler.saveById(id, false);

        /** See changes */
        System.out.println("ITEM ADDED");
        ordersCollection.showAllOrders();

        /** Remove existing element */
        ordersCollection.removeById(id);

        /** See changes */
        System.out.println("ITEM REMOVED");
        ordersCollection.showAllOrders();

        /** Load it back from file */
        fileHandler.readById(id, false);

        /** See changes */
        System.out.println("ITEM RESTORED");
        ordersCollection.showAllOrders();

        /** Clear all collection */
        ordersCollection.setOrdersList(new ArrayList<>());

        /** See changes */
        System.out.println("COLLECTION CLEARED");
        ordersCollection.showAllOrders();

        /** Restore it */
        fileHandler.readAll();

        /** See changes */
        System.out.println("COLLECTION RESTORED");
        ordersCollection.showAllOrders();
    }
}
