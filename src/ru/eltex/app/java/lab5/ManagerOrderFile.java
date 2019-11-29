package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.io.*;
import java.util.ArrayList;
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
        List<T> readCollection = new ArrayList<>();
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(fileNameToSave))) {
            while (readCollection.add((T) inStream.readObject()));
        } catch (EOFException eEOF) {
            // this is fine
        }
        catch (FileNotFoundException eFNF) {
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
        ordersCollection.setOrdersList(readCollection);

        return ordersCollection;
    }

    @Override
    public Orders<T> saveAll() {
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(fileNameToSave))) {
            List<T> actualOrdersList = ordersCollection.getOrdersList();
            for (T item : actualOrdersList) {
                outStream.writeObject(item);
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
