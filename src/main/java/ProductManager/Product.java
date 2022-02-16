package ProductManager;

public class Product {
    private final String name;
    private final double price;
    private final int qty;

    String category;

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
