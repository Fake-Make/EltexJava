package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

public class ThreadCheckAwaiting extends ACheck {
    public ThreadCheckAwaiting(Orders<Order> orderList) {
        super(orderList);
    }

    @Override
    public int process() {
        return ordersList.processElements();
    }

}
