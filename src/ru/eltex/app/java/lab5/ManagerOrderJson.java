package ru.eltex.app.java.lab5;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
        Orders<T> readCollection = new Orders<>();

        try (JsonReader inStream = new JsonReader(new FileReader(fileNameToSave))) {
            Gson gson = new Gson();
            /** The dark side of Java: reflection **/
            ArrayList<T> readList = gson.fromJson(inStream, ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            for (T item : readList) {
                readCollection.add(item);
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
        /**
         * NOTE: We replace whole existing collection
         * with collection that we just read.
         * Probably bad solution, but it depends of what we need.
         */
        ordersCollection = readCollection;

        return readCollection;
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
