package ShoppingCartManager;

import utils.Helper_fun;

public class Receipt {
    double subtotal;
    double tax;
    double total;

    public Receipt(double subtotal, double tax){
        this.subtotal = subtotal;
        this.tax = Helper_fun.myRound(tax);
        this.total = this.subtotal + this.tax;
    }

    public void show(){
        String format = "%-40s$%.2f%n";
        System.out.printf(format, "subTotal:", subtotal);
        System.out.printf(format, "tax:", tax);
        System.out.printf(format, "total:", total);
    }
}
