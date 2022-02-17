package ShoppingCartManager;

import config.env;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ShoppingCartManagerTest {
    @Test
    public void main(){
        String path = env.transaction_path+"transaction_3";
        ShoppingCartManager shoppingCartManager = new ShoppingCartManager(path);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        shoppingCartManager.printReceipt();
        String output = outContent.toString();
        assertTrue(output.contains("pencil"));
        assertTrue(output.contains("$0.55"));
        assertTrue(output.contains("$36.52"));
    }


}