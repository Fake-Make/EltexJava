package ru.eltex.app.java.lab5;

import com.google.gson.*;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.io.*;
import java.util.List;
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
        try (FileOutputStream outStream = new FileOutputStream(fileNameToSave)) {
            List<T> actualOrdersList = ordersCollection.getOrdersList();
            Gson gson = new Gson();
            byte[] buffer;
            for (T item : actualOrdersList) {
                buffer = ((String) gson.toJson(item)).getBytes();
                outStream.write(buffer);
            }
        } catch (FileNotFoundException eFNF) {
            System.out.println(eFNF);
            return null;
        } catch (IOException eIO) {
            System.out.println(eIO);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return ordersCollection;
    }
}
