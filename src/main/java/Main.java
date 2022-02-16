import ShoppingCartManager.ShoppingCartManager;
import config.env;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename;
        Scanner sc = new Scanner(System.in);
        if(args.length>0){
            filename = args[0];
        }
        else{
            System.out.print("Input the transaction file name: ");
            filename = sc.nextLine();
        }
        ShoppingCartManager shoppingCartManager = new ShoppingCartManager(env.transaction_path + filename);
    }
}
