package ru.eltex.app.java.factories;

import ru.eltex.app.java.lab1.Smartphone;

import java.util.concurrent.ThreadLocalRandom;

public class FactorySmartphones<T extends Smartphone> extends FactoryDevices<T> {
    protected T product;
    private int maxSimCards = 3;

    public FactorySmartphones() {
        product = (T) new Smartphone();
    }

    @Override
    public T produce() {
        product = (T) super.produce(product);

        try {
            product.setSimType(ThreadLocalRandom.current().nextInt(product.getSimTypes().length));
        } catch (Exception e) {
            // Well, it's okey, 'cos default sim type is 0 as int
        }

        try {
            product.setSimCount(ThreadLocalRandom.current().nextInt(maxSimCards) + 1);
        } catch (Exception e) {
            // Well, it's okey too, 'cos default sim count is 0 as int
        }

        return product;
    }
}
