package model;

public class Item implements Comparable<Item> {

  private int id;
  private String name; //name of the item
  private String measurement; //unit of measurement
  private double price; // price of the drink
  private double stock; //
  private String group;

  public Item(
    int id,
    String name,
    String measurement,
    double price,
    double stock,
    String group
  ) {
    this.id = id;
    this.name = name;
    this.measurement = measurement;
    this.price = price;
    this.stock = stock;
    this.group = group;
  }
  public Item(int id, String name, double price){
    this.id = id;
    this.name = name;
    this.price = price;
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
  public void setStock(double stock) {this.stock = stock; }
  public boolean decreaseStock(double amount) {
    if (amount <= stock) {
      stock -= amount;
      return true;
    } else {
      return false;
    }
  }
  public void increaseStock(double amount){
    this.stock = this.stock + amount;
  }
  @Override
  public String toString() {
    return String.format(
      "%5d %-15s %-15s %.2f€, %.2f %s im Inventar",
      id,
      group,
      name,
      price,
      stock,
      measurement );
  }

  @Override
  public int compareTo(Item other) {
    return Double.compare(this.price, other.price);
  }
}
