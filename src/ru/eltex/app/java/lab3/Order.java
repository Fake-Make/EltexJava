package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab1.ICrudAction;

import java.util.*;

/**
 * Order class
 * TODO: Develop CRUD-Methods for ShoppingCart class
 *
 * @author Dmitry Nevada
 * @version 1.19.11.19
 */
public class Order implements ICrudAction {
    /** Cart of ordered items */
    protected ShoppingCart cart;
    /** Customer */
    protected Credentials customer;
    /** Order status enum */
    protected enum Status {AWAITING, PROCESSED };
    /** Order status enum value */
    protected Status status;

    public Calendar getCreateTime() {
        return createTime;
    }

    /** Creating order date */
    protected Calendar createTime;

    protected UUID id;

    /** Amount of milliseconds in one hour */
    protected final long msInHour = 60 * 60 * 1000;
    /** Awaiting time limit in milliseconds */
    protected long awaitingTimeLimit = msInHour * 24 * 3;

    /** Default constructor with no params */
    public Order() {
        status = Status.AWAITING;
        id = UUID.randomUUID();
    }

    /** Overloaded constructor with cart and customer as Params */
    public Order(ShoppingCart cart, Credentials customer) {
        this.cart = cart;
        this.customer = customer;
        id = UUID.randomUUID();
        status = Status.AWAITING;
        createTime = new GregorianCalendar();
    }

    public static Order makePurchase(ShoppingCart cart, Credentials customer) {
        return new Order(cart, customer);
    }

    @Override
    public void create() {
        //cart.create();
        customer.create();
        status = Status.AWAITING;
        createTime = new GregorianCalendar();
    }

    @Override
    public void read() {
        System.out.println("Order ID:" + id);
        System.out.println("Order status: " + status.toString());
        System.out.println("Order created " + createTime.getTime());
        System.out.println("Shopping cart:");
        cart.showAll();
        System.out.println("Customer:");
        customer.read();
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);

        //cart.update();
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

        createTime.setTime(new Date());
    }

    @Override
    public void delete() {
        //cart.delete();
        customer.delete();
        status = null;
        createTime = null;
    }

    /**
     * Check order's awaiting time for expiring
     *
     * @return true if order's awaiting time is expired
     */
    public boolean isExpired() {
        return createTime.getTime().getTime() + awaitingTimeLimit <= System.currentTimeMillis();
    }

    /**
     * Check order status for processing
     *
     * @return true if status is PROCESSED
     */
    public boolean isProcessed() {
        return status == Status.PROCESSED;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Credentials getCustomer() {
        return customer;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
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
}
