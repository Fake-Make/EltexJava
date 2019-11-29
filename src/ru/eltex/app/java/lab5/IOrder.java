package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.UUID;

public interface IOrder<T extends Order> {
    /**
     * Read T-object from file and save it to collection
     *
     * @param id Field for search by in file
     * @param toRewrite Flag representing if existing item can be replaced by item from file with similar id
     * @return true if item from file was added to collection, false otherwise
     */
    boolean readById(UUID id, boolean toRewrite);
    /**
     * Same as readById, but loads T-object FROM collection TO file
     */
    boolean saveById(UUID id, boolean toRewrite);
    /**
     * Reads all T-objects from file and push it to collection
     * @return collection read from file
     */
    Orders<T> readAll();
    /**
     * Reads all T-objects from collection and save it to file
     * @return saved collection
     */
    Orders<T> saveAll();
}