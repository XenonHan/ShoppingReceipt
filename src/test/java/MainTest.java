import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void withPara() {
        String args[]={"transaction_1"};
        Main.main(args);
    }

    @Test
    public void withoutPara() {
        String args[] = {};
        String data = "transaction_2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Main.main(args);

    }

}