package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.UUID;

public interface IOrder<T extends Order> {
    boolean readById(UUID id, boolean toRewrite);
    boolean saveById(UUID id, boolean toRewrite);
    Orders<T> readAll();
    Orders<T> saveAll();
}
