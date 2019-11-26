package ru.eltex.app.java.factories;

import ru.eltex.app.java.lab3.Order;

public class Main {
    public static void main(String[] args) {
        AFactory<Order> factoryOrders = new FactoryOrders<>();
        factoryOrders.produce().read();
    }
}
