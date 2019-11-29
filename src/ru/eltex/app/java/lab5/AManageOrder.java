package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.UUID;

public abstract class AManageOrder implements IOrder {
    protected Orders<Order> ordersCollection;
    protected String fileNameToSave;

    public AManageOrder(Orders<Order> ordersCollection, String fileName) {
        this.ordersCollection = ordersCollection;
        this.fileNameToSave = fileName;
    }

    public String getFileNameToSave() {
        return fileNameToSave;
    }

    public void setFileNameToSave(String fileNameToSave) {
        this.fileNameToSave = fileNameToSave;
    }

    @Override
    public boolean readById(UUID id, boolean toRewrite) {
        boolean alreadyExists = ordersCollection.contains(id);

        if ( !toRewrite && alreadyExists )
            return false;

        Order itemFromFile = readAll().searchById(id);
        if (null != itemFromFile) {
            if (alreadyExists)
                ordersCollection.replace(itemFromFile);
            else
                ordersCollection.add(itemFromFile);
            return true;
        }
        return false;
    }
}
