package ru.eltex.app.java.lab4;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadCheckProcessed extends Thread {
    protected int runTimeout = 12, runTimeoutRange = runTimeout / 4;

    @Override
    public void run() {
        /** where to get collection of orders? */
        ACheck checker = new CheckProcessed();
        while (true) {
            checker.process();
            try {
                sleep(100 * ThreadLocalRandom.current().nextInt(runTimeout - runTimeoutRange, runTimeout + runTimeoutRange));
            } catch (InterruptedException e) {
                System.out.println("InterruptedException occurred at ThreadCheckProcessed: " + e);
            }
        }
    }
}
