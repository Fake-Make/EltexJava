package ru.eltex.app.java.factories;

import ru.eltex.app.java.lab1.Device;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FactoryDevices<T extends Device> extends AFactory<T> {
    private List<String> names = Arrays.asList("Lumia", "IPhone", "XPeria");
    private List<String> factories = Arrays.asList("Nokia", "Sony", "Apple");
    private List<String> models = Arrays.asList("3310", "X", "10", "SE", "Z3");
    private List<String> osArray = Arrays.asList("IOS", "Android");
    private double minPrice = 20.;
    private double maxPrice = 120.;

    public T produce(T product) {
        product.setName(getRandomString(names));
        product.setFactory(getRandomString(factories));
        product.setModel(getRandomString(models));
        product.setOs(getRandomString(osArray));
        product.setPrice(ThreadLocalRandom.current().nextDouble(minPrice, maxPrice));

        return product;
    }
}