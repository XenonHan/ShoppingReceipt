package TaxManager;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

// This class is used to store the tax rate and exempted categories a state in US
public class Tax {
    private final String state;
    private final BigDecimal taxRate;
    private final HashSet <String> exemptedCat;

    public Tax(String state, BigDecimal taxRate, String [] cat){
        this.state = state;
        this.taxRate = taxRate;
        this.exemptedCat = new HashSet<>(List.of(cat));
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public boolean isExempted(String cat) {
        return exemptedCat.contains(cat);
    }

    // print the variables
    public void show(){
        System.out.println("State: " + this.state);
        System.out.println("Tax: " + this.taxRate);
        System.out.println("Exempted Categories: " + this.exemptedCat);
    }
}
