package ShoppingCartManager;

import config.env;
import org.junit.Test;

public class ShoppingCartManagerTest {
    @Test
    public void main(){
        String path = env.transaction_path+"transaction_3";
        ShoppingCartManager shoppingCartManager = new ShoppingCartManager(path);
    }


}