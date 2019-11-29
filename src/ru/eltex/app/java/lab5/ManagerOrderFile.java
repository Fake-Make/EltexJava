package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.UUID;

public class ManagerOrderFile<T extends Order> extends AManageOrder<T>{
    @Override
    public boolean readById(UUID id, boolean toRewrite) {
        return super.readById(id, toRewrite);
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
