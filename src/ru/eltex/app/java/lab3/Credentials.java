package ru.eltex.app.java.lab3;
import ru.eltex.app.java.lab1.ICrudAction;

import java.util.Scanner;
import java.util.UUID;

/**
 * Credentials class
 *
 * @author Dmitry Nevada
 * @version 1.21.11.19
 */
public class Credentials implements ICrudAction {
    /** Person ID */
    private UUID id;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /** Person properties */
    private String firstName, surName, secondName, email;

    /** Default constructor */
    public Credentials() {
        id = UUID.randomUUID();
    }

    /** Overloaded constructor with basic params: First Name and E-mail */
    public Credentials(String firstName, String email) {
        id = UUID.randomUUID();
        this.firstName = firstName;
        this.email = email;
    }

    /** Overloaded constructor with all possible params */
    public Credentials(String firstName, String surName, String secondName, String email) {
        id = UUID.randomUUID();
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
        System.out.format("%s %s %s (%s) [%s]\n", firstName, secondName, surName, email, id.toString());
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
