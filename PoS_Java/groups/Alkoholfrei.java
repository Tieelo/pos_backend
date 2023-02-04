package PoS_Java.groups;
import PoS_Java.Items;

public class Alkoholfrei extends Items {

    public Alkoholfrei(String name, String measurement, double price, double stock) {
        super(name, measurement, price, stock);
    }

    public String toString() {
        return String.format("%-15s", "Alkoholfrei") + super.toString();
    }

}
