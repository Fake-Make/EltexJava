package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab2.Order;
import ru.eltex.app.java.lab2.OrderStatus;

public class CheckAwaiting extends ACheck {
    @Override
    public Order process(Order purchase) {
        if(purchase.getStatus() == OrderStatus.AWAITING)
            purchase.setStatus(OrderStatus.PROCESSED);
        return purchase;
    }
}
