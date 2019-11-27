package ru.eltex.app.java.lab4;

import ru.eltex.app.java.factories.AFactory;
import ru.eltex.app.java.factories.FactoryOrders;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

public class ThreadFactoryOrders extends ACheck {
    public ThreadFactoryOrders(Orders<Order> ordersList) {
        super(ordersList);
    }

    public ThreadFactoryOrders(Orders<Order> ordersList, long runTimeout) {
        super(ordersList);
        this.runTimeout = runTimeout;
    }

    /**
     * Add new Order to Orders-collection
     * @return current size of Orders-collection
     */
    @Override
    public int process() {
        AFactory<Order> factoryOrders = new FactoryOrders<>();
        ordersList.add(factoryOrders.produce());
        return ordersList.getOrdersAmount();
    }
}