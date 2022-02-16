package TaxManager;

import java.util.HashSet;
import java.util.List;

// This class is used to store the tax rate and exempted categories a state in US
public class Tax {
    private final String state;
    private final double tax_rate;
    private final HashSet <String> exempted_cat;

    public Tax(String state, double tax_rate, String [] cat){
        this.state = state;
        this.tax_rate = tax_rate;
        this.exempted_cat = new HashSet<>(List.of(cat));
    }

    public double getTax_rate() {
        return tax_rate;
    }

    public boolean isExempted(String cat) {
        return exempted_cat.contains(cat);
    }

    // print the variables
    public void show(){
        System.out.println("State: " + this.state);
        System.out.println("Tax: " + this.tax_rate);
        System.out.println("Exempted Categories: " + this.exempted_cat);
    }
}
