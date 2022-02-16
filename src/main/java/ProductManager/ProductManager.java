package ProductManager;

import TaxManager.Tax;
import config.env;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.Helper_fun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// This class classify different products to their categories
public class ProductManager {
    HashMap<String, String> product_cat;
    List<Product> product_list = new ArrayList<>();
    public ProductManager(){
        product_cat = new HashMap<>();
        System.out.print("loading product categories...");

        //load categories.json
        JSONArray product_arr = Helper_fun.load_json(env.config_path + "/categories.json");
        product_arr.forEach(data -> parseCategories((JSONObject) data));
        System.out.println("Done");
    }

    // match a product with its categories, then store in product_cat
    public void parseCategories(JSONObject arr){
        String cat = (String) arr.get("category");
        JSONArray product_list = (JSONArray) arr.get("product");
        String[] products = new String[product_list.size()];
        Helper_fun.JSONArray_to_list(product_list).toArray(products);
        for(String product : products){
            product_cat.put(product,cat);
        }

    }

    // Get the category of a product
    public String getCategory(String product){
        String cat = product_cat.get(product);
        return cat!=null?cat:"others";
    }

    // Create new product and store in product_list
    public void addProduct(String name, double price, int qty){
        if (name==null) return;
        String cat = getCategory(name);
        Product product = new Product(name, price, qty, cat);
        product_list.add(product);
    }

    public void show(){
        product_list.forEach(i->i.show());
    }

    // cal the subtotal of all products store in product_list
    public double calSubTotal(){
        double subtotal = 0;
        double tax = 0;
        for(Product product:product_list){
            subtotal += product.totalPrice();
        }
        return subtotal;
    }

    // cal the tax of all product store in product_list
    public double calTax(Tax tax){
        double tax_rate = tax.getTax_rate();
        double total_tax = 0;
        for (Product product: product_list){
            if (!tax.isExempted(product.category)){
                total_tax += product.totalPrice() * tax_rate;
            }
        }
        return total_tax;
    }


}
