package TaxManager;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TaxTest {
    Tax tax = new Tax("NY", new BigDecimal("0.25"), new String[]{"PS3", "PS4", "PS5"});
    @Test
    public void getTax_rate() {
        BigDecimal tax_rate = tax.getTaxRate();
        assertEquals(BigDecimal.valueOf(0.25), tax_rate);
    }

    @Test
    public void isExempted() {
        assertTrue(tax.isExempted("PS3"));
        assertFalse(tax.isExempted("PlayStation"));
    }

    @Test
    public void show() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        tax.show();
        String output = outContent.toString();
        assertTrue(output.contains("State: NY"));
        assertTrue(output.contains("Tax: 0.25"));
        assertTrue(output.contains("Exempted Categories: [PS4, PS3, PS5]"));
    }
}