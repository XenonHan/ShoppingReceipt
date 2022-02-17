package ProductManager;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final int qty;

    String category;

    public Product(String name, BigDecimal price, int qty, String category){
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.category = category;
    }

    public BigDecimal totalPrice() {
        return price.multiply(BigDecimal.valueOf(qty));
    }

    public void show(){
        String format = "%-20s$%-20.2f%d%n";
        System.out.printf(format, name, price, qty);
    }

}
