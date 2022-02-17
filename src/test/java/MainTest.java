import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;


public class MainTest {

    @Test
    public void withPara() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args={"transaction_1"};
        Main.main(args);
        String output = outContent.toString();
        assertTrue(output.contains("loading tax data...Done"));
        assertTrue(output.contains("loading product categories...Done"));
        assertTrue(output.contains("loading transaction in ./src/main/java/transaction/transaction_1.xlsx...Done"));
    }

    @Test
    public void withoutPara() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {};
        String data = "transaction_2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Main.main(args);
        String output = outContent.toString();
        assertTrue(output.contains("loading tax data...Done"));
        assertTrue(output.contains("loading product categories...Done"));
        assertTrue(output.contains("loading transaction in ./src/main/java/transaction/transaction_2.xlsx...Done"));



    }

}