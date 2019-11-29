package ru.eltex.app.java.lab5;

import ru.eltex.app.java.factories.FactoryOrders;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        String commonFile = "commonFile", jsonFile = "jsonFile";
        try {
            Files.deleteIfExists(Path.of(commonFile));
            Files.deleteIfExists(Path.of(jsonFile));
        } catch (Exception e) {
            // whatever
        }

        /** Init variables */
        Orders<Order> ordersCollection = new Orders<>();
        Orders<Order> ordersCollectionAfter = new Orders<>();
        FactoryOrders<Order> factoryOrders = new FactoryOrders<>();

        /** Init managers */
        AManageOrder commonFileSaver = new ManagerOrderFile(ordersCollection, commonFile);
        AManageOrder commonFileReader = new ManagerOrderFile(ordersCollectionAfter, commonFile);
        AManageOrder jsonFileSaver = new ManagerOrderJson(ordersCollection, jsonFile);
        AManageOrder jsonFileReader = new ManagerOrderJson(ordersCollectionAfter, jsonFile);

        /** Produce orders */
        ordersCollection.add(factoryOrders.produce());
        ordersCollection.add(factoryOrders.produce());
        ordersCollection.add(factoryOrders.produce());

        /** Save files */
        commonFileSaver.saveAll();
        jsonFileSaver.saveAll();

        /** Load common file */
        commonFileReader.readAll();

        /** See changes */
        ordersCollectionAfter.showAllOrders();

        /** Add new element */
        Order tmp = factoryOrders.produce();
        System.out.println();
        System.out.println("new ID:" + tmp.getId());
        ordersCollectionAfter.add(tmp);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID для сохранения в файл");
        UUID id = UUID.fromString(scanner.nextLine());

        /** Save it to file */
        commonFileReader.saveById(id, false);

        /** See changes */
        System.out.println("ITEM ADDED");
        ordersCollectionAfter.showAllOrders();

        /** Remove existing element */
        ordersCollectionAfter.removeById(id);

        /** See changes */
        System.out.println("ITEM REMOVED");
        ordersCollectionAfter.showAllOrders();

        /** Load it back from file */
        commonFileReader.readById(id, false);

        /** See changes */
        System.out.println("ITEM GOT BACK");
        ordersCollectionAfter.showAllOrders();

        /** Load json file */
        //jsonFileReader.readAll();

        /** See changes */
        //ordersCollectionAfter.showAllOrders();
    }
}
