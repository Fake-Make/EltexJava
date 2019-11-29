package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.UUID;

public class ManagerOrderJson<T extends Order> extends AManageOrder<T> {
    public ManagerOrderJson(Orders<T> ordersCollection, String fileName) {
        super(ordersCollection, fileName);
    }

    @Override
    public boolean saveById(UUID id, boolean toRewrite) {
        return false;
    }

    @Override
    public Orders<T> readAll() {
        return null;
    }

    @Override
    public Orders<T> saveAll() {
        return null;
    }
}
