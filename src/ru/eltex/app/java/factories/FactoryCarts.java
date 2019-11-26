package ru.eltex.app.java.factories;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;
import ru.eltex.app.java.lab3.ShoppingCart;

import java.util.concurrent.ThreadLocalRandom;

public class FactoryCarts<T extends ShoppingCart<Device>> extends AFactory<T> {
    private T cart;
    private int minCartSize = 3, maxCartSize = 6;
    private int deviceTypes = 3;

    public FactoryCarts() {
        cart = (T) new ShoppingCart<>();
    }

    @Override
    public T produce() {
        int range = ThreadLocalRandom.current().nextInt(minCartSize, maxCartSize);
        for (int i = 0; i < range; i++) {
            Device item;
            FactoryDevices<Phone> factoryPhones;
            FactoryDevices<Smartphone> factorySmartphones;
            FactoryDevices<Tablet> factoryTablets;

            switch (ThreadLocalRandom.current().nextInt(deviceTypes)) {
                case 0:
                    factoryPhones = new FactoryPhones<>();
                    item = factoryPhones.produce();
                break;
                case 1:
                    factorySmartphones = new FactorySmartphones<>();
                    item = factorySmartphones.produce();
                break;
                default:
                    factoryTablets = new FactoryTablets<>();
                    item = factoryTablets.produce();
            }
            cart.add(item);
        }

        return cart;
    }
}
