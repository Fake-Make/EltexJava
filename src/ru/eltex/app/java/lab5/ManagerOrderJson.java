package ru.eltex.app.java.lab5;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.io.*;
import java.lang.reflect.ParameterizedType;
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

        try (JsonReader inStream = new JsonReader(new FileReader(fileNameToSave))) {
            Gson gson = new Gson();
            /** The dark side of Java: reflection **/
            readList = gson.fromJson(inStream, ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
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
        /**
         * NOTE: We replace whole existing collection
         * with collection that we just read.
         * Probably bad solution, but it depends of what we need.
         */
        ordersCollection.setOrdersList(readList);

        return ordersCollection;
    }

    @Override
    public Orders<T> saveAll() {
        try (FileOutputStream outStream = new FileOutputStream(fileNameToSave)) {
            List<T> actualOrdersList = ordersCollection.getOrdersList();
            Gson gson = new Gson();

            for (T item : actualOrdersList) {
                outStream.write(gson.toJson(item).getBytes());
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
