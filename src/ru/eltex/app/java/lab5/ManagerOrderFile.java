package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class ManagerOrderFile<T extends Order> extends AManageOrder<T>{
    public ManagerOrderFile(Orders<T> ordersCollection, String fileName) {
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
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(fileNameToSave))) {
            List<T> ordersToSave = readCollection.getOrdersList();
            for (T item : ordersToSave) {
                outStream.writeObject(item);
            }
        } catch (FileNotFoundException eFNF) {
            System.out.println(eFNF);
            return false;
        } catch (IOException eIO) {
            System.out.println(eIO);
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    @Override
    public Orders<T> readAll() {
        Orders<T> readCollection = new Orders<>();
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(fileNameToSave))) {
            T tempObject;
            while(null != (tempObject = (T) inStream.readObject())) {
                readCollection.add(tempObject);
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
        return null;
    }
}
