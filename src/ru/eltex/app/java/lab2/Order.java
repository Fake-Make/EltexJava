package ru.eltex.app.java.lab2;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.ICrudAction;

import java.util.Date;
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
    /** Order status enum */
    protected enum Status {AWAITING, PROCESSED };
    /** Order status enum value */
    protected Status status;
    /** Creating order date */
    protected Date createTime;

    /** Bad solution? */
    /** Amount of milliseconds in one day */
    private long msInDay = 24 * 60 * 60 * 1000;
    /** Awaiting time limit in milliseconds */
    protected final long awaitingTimeLimit = msInDay * 3;

    /** Default constructor with no params */
    public Order() {
        status = Status.AWAITING;
    }

    /** Overloaded constructor with item and customer as Params */
    public Order(Device item, Credentials customer) {
        this.item = item;
        this.customer = customer;
        status = Status.AWAITING;
        createTime = new Date();
    }

    @Override
    public void create() {
        item.create();
        customer.create();
        status = Status.AWAITING;
        createTime = new Date();
    }

    @Override
    public void read() {
        System.out.println("Ordered item:");
        item.read();
        System.out.println("Customer:");
        customer.read();
        System.out.println("Order status: " + status.toString());
        System.out.println("Order created " + createTime.toString());
    }

    public Device getItem() {
        return item;
    }

    public Credentials getCustomer() {
        return customer;
    }

    public void setItem(Device item) {
        this.item = item;
    }

    public void setCustomer(Credentials customer) {
        this.customer = customer;
    }

    public int setStatus(String status) {
        try {
            this.status = Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            this.status = Status.AWAITING;
            return 1;
        } catch (NullPointerException e) {
            this.status = Status.AWAITING;
            return 2;
        }
        return 0;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);

        item.update();
        customer.update();

        System.out.println("Please enter order status:");
        switch (setStatus(scanner.nextLine())) {
            case 1:
                System.out.println("There is no such status;");
                System.out.println("Order status is " + status.toString() + " by default;");
                break;
            case 2:
                System.out.println("Entered status is empty;");
                System.out.println("Order status is " + status.toString() + " by default;");
                break;
        }

        createTime.setTime(System.currentTimeMillis());
    }

    @Override
    public void delete() {
        item.delete();
        customer.delete();
        status = null;
        createTime = null;
    }
}
