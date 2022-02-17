package ProductManager;

import TaxManager.Tax;
import config.env;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.HelperFunction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// This class classify different products to their categories
public class ProductManager {
    private final HashMap<String, String> productCategories;
    private final List<Product> productList = new ArrayList<>();
    @SuppressWarnings("unchecked")
    public ProductManager(){
        productCategories = new HashMap<>();
        System.out.print("loading product categories...");

        //load categories.json
        JSONArray product_arr = HelperFunction.load_json(env.config_path + "/categories.json");
        product_arr.forEach(data -> parseCategories((JSONObject) data));
        System.out.println("Done");
    }

    // match a product with its categories, then store in product_cat

    public void parseCategories(JSONObject arr){
        String cat = (String) arr.get("category");
        JSONArray product_list = (JSONArray) arr.get("product");
        String[] products = new String[product_list.size()];
        HelperFunction.JSONArray_to_list(product_list).toArray(products);
        for(String product : products){
            productCategories.put(product,cat);
        }

    }

    // Get the category of a product
    public String getCategory(String product){
        String cat = productCategories.get(product);
        return cat!=null?cat:"other";
    }

    // Create new product and store in product_list
    public void addProduct(String name, BigDecimal price, int qty){
        if (name==null) return;
        String cat = getCategory(name);
        Product product = new Product(name, price, qty, cat);
        productList.add(product);
    }

    public void show(){
        productList.forEach(i->i.show());
    }

    // cal the subtotal of all products store in product_list
    public BigDecimal calSubTotal(){
        BigDecimal subtotal = new BigDecimal(0);
        for(Product product:productList){
            subtotal = subtotal.add(product.totalPrice());
        }
        return subtotal;
    }

    // cal the tax of all product store in product_list
    public BigDecimal calTax(Tax tax){
        BigDecimal taxRate = tax.getTaxRate();
        BigDecimal totalTax = new BigDecimal(0);
        for (Product product: productList){
            if (!tax.isExempted(product.category)){
                totalTax = totalTax.add(product.totalPrice().multiply(taxRate)) ;
            }
        }
        return totalTax;
    }


}
