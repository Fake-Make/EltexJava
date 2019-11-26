package ru.eltex.app.java.lab4;

public class CheckAwaiting extends ACheck {
    @Override
    public int process() {
        return ordersList.processElements();
    }
}