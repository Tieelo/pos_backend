package PoS_Java.groups;
import PoS_Java.Items;

public class Wein extends Items {

    public Wein(String name, String measurement, double price, double stock) {
        super(name, measurement, price, stock);
    }

    public String toString() {
        return String.format("%-15s", "Wein") + super.toString();
    }
}
