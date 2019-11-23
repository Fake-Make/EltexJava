package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        if (2 > args.length) {
            System.out.println("Too few arguments!");
            return;
        }
        int devicesAmount = Integer.parseInt(args[0]);
        String deviceType = args[1];

        /** create two persons */
        Credentials personOne = new Credentials("Ivan", "Ivanovich", "Ivanov", "mail@mail.ru");
        Credentials personTwo = new Credentials();
        personTwo.create();

        /** create five devices */
        List<Device> devices = new ArrayList<>();
        for (int i = 0; i < devicesAmount; i++) {
            Device device;
            switch (deviceType) {
                case "Phone":
                    device = new Phone();
                    break;
                case "Smartphone":
                    device = new Smartphone();
                    break;
                default:
                    device = new Tablet();
            }
            device.create();
            devices.add(device);
        }

        /** create two baskets */
        ShoppingCart<Device> basketOne = new ShoppingCart<>();
        ShoppingCart<Device> basketTwo = new ShoppingCart<>();

        basketOne.add(devices.get(0));
        basketOne.add(devices.get(1));

        basketTwo.add(devices.get(2));
        basketTwo.add(devices.get(3));
        basketTwo.add(devices.get(4));

        /** create two orders */
        Orders<Order> orders = new Orders<>();
        orders.makePurchase(basketOne, personOne);
        orders.makePurchase(basketTwo, personTwo);

        /** display all of them */
        System.out.println("Persons:");
        personOne.read();
        personTwo.read();

        System.out.println("Stall:");
        for (Device item : devices) {
            item.read();
        }

        System.out.println("Orders:");
        orders.showAllOrders();


        System.out.println();
        System.out.println();
        System.out.println("First basket:");
        basketOne.showAll();
        System.out.println();

        /** deleting item from cart */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID to delete this item from first cart:");
        /** also searching item by id */
        UUID searchId = UUID.fromString(scanner.nextLine());
        System.out.println("UUID from string is " + searchId);
        Device foundItem = basketOne.searchById(searchId);
        if (foundItem == null) {
            System.out.println("I can't find it");
        } else {
            if (1 == basketOne.delete(foundItem)) {
                System.out.println("There wasn't such item");
            } else {
                /** and displaying that */
                System.out.println("Now first basket looks like this:");
                basketOne.showAll();
            }
        }
    }
}
