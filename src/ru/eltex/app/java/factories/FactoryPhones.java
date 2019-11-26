package ru.eltex.app.java.factories;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;

import java.util.concurrent.ThreadLocalRandom;

public class FactoryPhones<T extends Phone> extends FactoryDevices<T> {
    protected T product;

    public FactoryPhones() {
        product = (T) new Phone();
    }

    @Override
    public T produce() {
        product = (T) super.produce(product);

        try {
            product.setFormType(ThreadLocalRandom.current().nextInt(product.getFormTypes().length));
        } catch (Exception e) {
            // Well, it's okey, 'cos default form type is 0 as int
        }

        return product;
    }
}
