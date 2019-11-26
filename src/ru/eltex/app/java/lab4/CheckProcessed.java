package ru.eltex.app.java.lab4;

public class CheckProcessed extends ACheck {
    @Override
    public int process() {
        return ordersList.removeExpiredElements(false);
    }
}
