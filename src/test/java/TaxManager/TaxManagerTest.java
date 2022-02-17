package TaxManager;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TaxManagerTest {
    TaxManager taxManager = new TaxManager();

    @Test
    public void showTax() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        taxManager.showTax();
        String output = outContent.toString();
        assertTrue(output.contains("Exempted Categories: [clothing, food]"));
        assertTrue(output.contains("Exempted Categories: [food]"));
    }


}