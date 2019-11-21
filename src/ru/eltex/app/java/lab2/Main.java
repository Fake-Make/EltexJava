package ru.eltex.app.java.lab2;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        /** create two persons */
        Credentials personOne = new Credentials("Ivan", "Ivanovich", "Ivanov", "mail@mail.ru");
        Credentials personTwo = new Credentials();
        personTwo.create();

        /** create five devices */
        ArrayList<Device> devices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Device device = new Phone();
            device.create();
            devices.add(device);
        }

        /** create two baskets */
        ShoppingCart basketOne = new ShoppingCart();
        ShoppingCart basketTwo = new ShoppingCart();

        basketOne.add(devices.get(0));
        basketOne.add(devices.get(1));

        basketTwo.add(devices.get(2));
        basketTwo.add(devices.get(3));
        basketTwo.add(devices.get(4));

        /** create two orders */
        Orders orders = new Orders();
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

        /** deleting item from cart */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID to delete this item from first cart:");
        /** also searching item by id */
        if (-1 == basketOne.delete(basketOne.searchById(UUID.fromString(scanner.nextLine())))) {
            System.out.println("There wasn't such item");
        } else {
            /** and displaying that */
            System.out.println("Now first basket looks like this:");
            basketOne.showAll();
        }
    }
}
