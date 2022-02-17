package ShoppingCartManager;

import utils.HelperFunction;

import java.math.BigDecimal;

public class Receipt {
    private final BigDecimal subtotal;
    private final BigDecimal tax;
    private final BigDecimal total;

    public Receipt(BigDecimal subtotal, BigDecimal tax){
        this.subtotal = subtotal;
        this.tax = HelperFunction.myRound(tax);
        this.total = this.subtotal.add(this.tax);
    }

    public void show(){
        String format = "%-40s$%.2f%n";
        System.out.printf(format, "subTotal:", subtotal);
        System.out.printf(format, "tax:", tax);
        System.out.printf(format, "total:", total);
    }
}
