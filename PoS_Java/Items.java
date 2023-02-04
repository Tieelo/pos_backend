package PoS_Java;
public class Items {

    private int id;
    private String name;  //name of the item
    private String measurement; //unit of measurement
    private double price; // price of the drink
    private double stock; //
    private String group;

    public Items(int id, String name, String measurement, double price, double stock, String group) {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
        this.price = price;
        this.stock = stock;
        this.group = group;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
    public double getStock() {
        return stock;
    }
    public boolean decreaseStock(double amount) {
        if (amount <= stock){
            stock -=amount;
            return true;
        }else{
            return false;
        }
    }
    @Override
    public String toString() {
        return String.format("%-15s %-15s %.2f€, %.2f %s im Inventar", group, name, price, stock, measurement);
    }

}

