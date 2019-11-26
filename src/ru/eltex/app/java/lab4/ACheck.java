package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab2.Order;

/**
 * ACheck class
 * Check and change orders' status
 *
 * @author Dmitry Nevada
 * @version 0.26.11.19
 */
public abstract class ACheck {
    /**
     * Checks orders' status and change it
     *
     * @param purchase required order to check status
     * @return changed Order object
     */
    public abstract Order process(Order purchase);
}
