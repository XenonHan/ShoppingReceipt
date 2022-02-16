package TaxManager;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaxManagerTest {
    TaxManager taxManager = new TaxManager();

    @Test
    public void showTax() {
        taxManager.showTax();
    }


}