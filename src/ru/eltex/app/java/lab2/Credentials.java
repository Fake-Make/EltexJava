package ru.eltex.app.java.lab2;
import ru.eltex.app.java.lab1.ICrudAction;

import java.util.Scanner;
import java.util.UUID;

public class Credentials implements ICrudAction {
    private UUID id;
    private String firstName, surName, secondName, email;

    public Credentials() {}

    public Credentials(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public Credentials(String firstName, String surName, String secondName, String email) {
        this.firstName = firstName;
        this.surName = surName;
        this.secondName = secondName;
        this.email = email;
    }

    @Override
    public void create() {
        firstName = "John";
        surName = "Donne";
        secondName = "J";
        email = "john@mail.sample";
    }

    @Override
    public void read() {
        System.out.println(
                "Person ID: " + id + ";"
        );
        System.out.println(
                "Person: " +
                        (null == firstName ? "" : firstName + " ") +
                        (null == surName ? "" : surName + " ") +
                        (null == secondName ? "" : secondName) +
                        ";"
        );
        System.out.println(
                null == email ? "No e-mail;" : "E-mail: " + email + ";"
        );
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your e-mail:");
        email = scanner.nextLine();
        System.out.println("Please enter your first name:");
        firstName = scanner.nextLine();
        System.out.println("Please enter your surname:");
        surName = scanner.nextLine();
        System.out.println("Please enter your second name (if exists):");
        secondName = scanner.nextLine();
    }

    @Override
    public void delete() {
        surName = firstName = secondName = email = null;
    }
}
