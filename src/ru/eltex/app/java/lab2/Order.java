package ru.eltex.app.java.lab2;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.ICrudAction;
import java.util.Scanner;

/**
 * Order class
 *
 * @author Dmitry Nevada
 * @version 0.18.11.19
 */
public class Order implements ICrudAction {
    /** Ordered item */
    protected Device item;
    /** Customer */
    protected Credentials customer;
    /** Order status (Waiting | Processed) */
    //protected enum status {};

    /** Default constructor with no params */
    public Order() {}

    /** Overloaded constructor with item and customer as Params */
    public Order(Device item, Credentials customer) {
        this.item = item;
        this.customer = customer;
    }

    @Override
    public void create() {
        item.create();
        customer.create();
    }

    @Override
    public void read() {
        System.out.println("Ordered item:");
        item.read();
        System.out.println("Customer:");
        customer.read();
    }

    @Override
    public void update() {
        item.update();
        customer.update();
    }

    @Override
    public void delete() {
        item.delete();
        customer.delete();
    }
}
