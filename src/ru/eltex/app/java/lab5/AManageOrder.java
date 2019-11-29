package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.util.UUID;

public abstract class AManageOrder<T extends Order> implements IOrder<T> {
    protected Orders<T> ordersCollection;
    protected String fileNameToSave = "io.file";

    @Override
    public boolean readById(UUID id, boolean toRewrite) {
        /**
         * TODO:
         * Orders.searchById()
         * Orders.containsById()
         * Orders.removeById()
         */

        /**
         * if ( !toRewrite && orderCollection.containsById(id) )
         *      return false;
         * Orders<T> tmp = readAll();
         * if (tmp.containById(id)) {
         *      if ( orderCollection.containsById(id) ) {
         *          // replace
         *          orderCollection.removeById(id);
         *          orderCollection.add(tmp.searchById(id));
         *      } else {
         *          // add
         *          orderCollection.add(tmp.searchById(id));
         *      }
         *      return true;
         * }
         */
        return false;
    }
}
