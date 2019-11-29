package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.UUID;

public abstract class AManageOrder<T extends Order> implements IOrder<T> {
    protected Orders<T> ordersCollection;
    protected String fileNameToSave;

    public AManageOrder(Orders<T> ordersCollection, String fileName) {
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

        T itemFromFile = readAll().searchById(id);
        if (null != itemFromFile) {
            if (alreadyExists) {
                // Replace
                ordersCollection.removeById(id);
                ordersCollection.add(itemFromFile);
            } else {
                // Add
                ordersCollection.add(itemFromFile);
            }
            return true;
        }
        return false;
    }
}
