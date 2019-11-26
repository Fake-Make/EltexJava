package ru.eltex.app.java.factories;

import ru.eltex.app.java.lab3.Credentials;

import java.util.Arrays;
import java.util.List;

public class FactoryPersons<T extends Credentials> extends AFactory<T> {
    protected T person;
    private List<String> names = Arrays.asList("Michael", "Michelle", "Suzy", "Ivan", "Alex");
    private List<String> surNames = Arrays.asList("Simpson", "Down", "Garfield", "Stark");
    private List<String> secondNames = Arrays.asList("Jay", "Joe", "Abbey", "Aaron", "Smith");
    private List<String> emails = Arrays.asList("mail@mail.ru", "mail@gmail.com", "test@test.test");

    public FactoryPersons() {
        person = (T) new Credentials();
    }

    @Override
    public T produce() {
        person.setFirstName(getRandomString(names));
        person.setSurName(getRandomString(surNames));
        person.setSecondName(getRandomString(secondNames));
        person.setEmail(getRandomString(emails));

        return person;
    }
}
