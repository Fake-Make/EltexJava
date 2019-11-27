package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

public class ThreadCheckProcessed extends ACheck {
    public ThreadCheckProcessed(Orders<Order> orderList) {
        super(orderList);
    }

    @Override
    public int process() {
        return ordersList.removeExpiredElements(false);
    }
}
