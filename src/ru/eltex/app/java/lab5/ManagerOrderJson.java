package ru.eltex.app.java.lab5;

import com.google.gson.*;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManagerOrderJson extends AManageOrder {
    public ManagerOrderJson(Orders<Order> ordersCollection, String fileName) {
        super(ordersCollection, fileName);
    }

    @Override
    public boolean saveById(UUID id, boolean toRewrite) {
        Order itemToSave = ordersCollection.searchById(id);

        if (null == itemToSave)
            return false;

        Orders<Order> readCollection = readAll();
        if (!toRewrite && readCollection.contains(id))
            return false;

        readCollection.replace(itemToSave);
        try (FileOutputStream outStream = new FileOutputStream(fileNameToSave)) {
            List<Order> ordersToSave = readCollection.getOrdersList();
            Gson gson = new Gson();

            for (Order item : ordersToSave) {
                outStream.write(gson.toJson(item).getBytes());
            }
        } catch (FileNotFoundException eFNF) {
            eFNF.printStackTrace();
            return false;
        } catch (IOException eIO) {
            eIO.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Orders<Order> readAll() {
        ArrayList<Order> readList = new ArrayList<>();

        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(fileNameToSave))) {
            Gson gson = new Gson();
            while (readList.add((Order) gson.fromJson((String) inStream.readObject(), Order.class)));
        } catch (EOFException eEOF) {
            // this is fine
        } catch (FileNotFoundException eFNF) {
            eFNF.printStackTrace();
            return null;
        } catch (IOException eIO) {
            eIO.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        ordersCollection.setOrdersList(readList);

        return ordersCollection;
    }

    @Override
    public Orders<Order> saveAll() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileNameToSave))) {
            List<Order> actualOrdersList = ordersCollection.getOrdersList();
            Gson gson = new Gson();

            for (Order item : actualOrdersList) {
                outputStream.writeObject(gson.toJson(item, Order.class));
            }
        } catch (FileNotFoundException eFNF) {
            eFNF.printStackTrace();
            return null;
        } catch (IOException eIO) {
            eIO.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ordersCollection;
    }
}
