package ru.eltex.app.java.factories;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Factory abstract class
 *
 * @author Dmitry Nevada
 * @version 1.26.11.19
 */
public class AFactory<T> implements IFactory<T> {
    public AFactory() {};

    public T produce() {
        return null;
    }

    protected String getRandomString(List<String> list) {
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }
}
