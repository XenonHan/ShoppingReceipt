package TaxManager;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaxTest {
    Tax tax = new Tax("NY",0.25, new String[]{"PS3", "PS4", "PS5"});
    @Test
    public void getTax_rate() {
        double tax_rate = tax.getTax_rate();
        assertEquals(0.25, tax_rate, 0.00001);
    }

    @Test
    public void isExempted() {
        assertEquals(true, tax.isExempted("PS3"));
        assertEquals(false, tax.isExempted("PlayStation"));
    }

    @Test
    public void show() {
        tax.show();
    }
}