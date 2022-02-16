package ProductManager;

public class Product {
    String name;
    String category;
    double price;
    int qty;

    public Product(String name, double price, int qty, String category){
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.category = category;
    }

    public double totalPrice() {
        return price * qty;
    }

    public void show(){
        String format = "%-20s$%-20.2f%d%n";
        System.out.printf(format, name, price, qty);
    }

}
