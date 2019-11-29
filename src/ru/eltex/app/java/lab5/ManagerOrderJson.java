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

public class ManagerOrderJson<T extends Order> extends AManageOrder<T> {
    public ManagerOrderJson(Orders<T> ordersCollection, String fileName) {
        super(ordersCollection, fileName);
    }

    @Override
    public boolean saveById(UUID id, boolean toRewrite) {
        T itemToSave = ordersCollection.searchById(id);

        if (null == itemToSave)
            return false;

        Orders<T> readCollection = readAll();
        if (!toRewrite && readCollection.contains(id))
            return false;

        readCollection.replace(itemToSave);
        try (FileOutputStream outStream = new FileOutputStream(fileNameToSave)) {
            List<T> ordersToSave = readCollection.getOrdersList();
            Gson gson = new Gson();

            for (T item : ordersToSave) {
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
    public Orders<T> readAll() {
        ArrayList<T> readList;

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
