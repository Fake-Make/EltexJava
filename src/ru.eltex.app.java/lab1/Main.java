package ru.eltex.app.java.lab1;

/**
 * Main class for launching first lab
 *
 * @author Dmitry Nevada
 * @version 0.17.09.19
 *
 * @throws Exception if price is lower than 0
 */
public class Main {
    public static void main(String[] args) throws Exception {
        //Device gadget = new Phone("Heighscreen", 12.3);
        Device gadget = new Phone();
        gadget.read();
        gadget.update();
        gadget.read();
        //gadget.read();
        //System.out.println();
        /*
        Device gadget2 = new Phone("Heighscreen", "Heighscreen", "Zera F", "Android", 25.0, 0);
        gadget2.read();
        System.out.println();
        gadget2.delete();
        gadget2.read();
        System.out.println();
        gadget2.create();
        gadget2.read();
         */
	/*
		count = (int)args[0] > 0 ? (int)args[0] : -1;
		view = in_array(args[1], ar = ["phone", "smartphone", "tablet"]) ?
			index_of(args[1], ar) :
			-1;

		if (count < 0 || view < 0) {
			System.out.println("Input error: incorrect data;");
			return 1;
		}

		for (int i = 0; i < count; i++) {
			System.in.readln(params);
			switch(view) {
				case 0:
					Device gadget = new Phone(params);
					break;
				case 1:
					Device gadget = new Smartphone(params);
					break;
				case 2:
					Device gadget = new Tablet(params);
					break;
			}
		}
	*/
    }
}