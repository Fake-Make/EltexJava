package ru.eltex.app.java.factories;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab3.Credentials;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.ShoppingCart;

public class FactoryOrders<T extends Order> extends AFactory<T> {
    private T order;

    @Override
    public T produce() {
        AFactory<ShoppingCart<Device>> factoryCarts = new FactoryCarts<>();
        AFactory<Credentials> factoryPersons = new FactoryPersons<>();

        order = (T) T.makePurchase(factoryCarts.produce(), factoryPersons.produce());
        return order;
    }
}
