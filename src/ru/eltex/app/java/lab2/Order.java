package ru.eltex.app.java.lab2;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.ICrudAction;
import java.util.Scanner;

public class Order implements ICrudAction {
    protected Device item;
    protected Credentials customer;
    //protected enum status {};

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
