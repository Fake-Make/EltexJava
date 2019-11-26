package ru.eltex.app.java.factories;

import ru.eltex.app.java.lab1.Tablet;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FactoryTablets<T extends Tablet> extends FactoryDevices<T> {
    protected T product;
    private List<String> videoChips = Arrays.asList("Dragon", "Radeon HD", "NVidia GeForce");
    private List<String> screenResolutions = Arrays.asList("720x1080", "820x1280", "1280x1660");

    public FactoryTablets() {
        product = (T) new Tablet();
    }

    @Override
    public T produce() {
        product = (T) super.produce(product);

        product.setVideoChip(videoChips.get(ThreadLocalRandom.current().nextInt(videoChips.size())));
        product.setScreenResolution(screenResolutions.get(ThreadLocalRandom.current().nextInt(screenResolutions.size())));

        return product;
    }
}
