package ru.eltex.app.java.lab1;

/**
 * Main class for launching first lab
 *
 * @author Dmitry Nevada
 * @version 0.17.09.19
 */
public class Main {
    /**
     * Main user interface
     *
     * @param args
     * @throws Exception if price is lower than 0
     * @throws Exception if simType or formType is incorrect
     *
     * @todo obj.create() && obj.read() in cycle
     */
    public static void main(String[] args) throws Exception {
        if (2 > args.length)
            throw new Exception("Too few arguments!");
        int N = Integer.parseInt(args[0]);
        String deviceType = args[1];
        Device obj;

        for (int i = 0; i < N; i++) {
            switch (deviceType) {
                case "Phone":
                    obj = new Phone();
                    break;
                case "Smartphone":
                    obj = new Smartphone();
                    break;
                case "Tablet":
                    obj = new Tablet();
                    break;
                default:
                    throw new Exception("There is no such type of device!");
            }
            obj.update();
            obj.read();
        }
    }
}