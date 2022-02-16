package TaxManager;
import config.env;
import utils.Helper_fun;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;


// this class load the tax info of various states from Tax.json
public class TaxManager {
    private final HashMap<String, Tax> tax_list;
    @SuppressWarnings("unchecked")
    public TaxManager(){
        tax_list = new HashMap<>();
        System.out.print("loading tax data...");

        //load the tax.json
        JSONArray state_arr = Helper_fun.load_json(env.config_path+"/tax.json");
        state_arr.forEach(data -> parseTax((JSONObject) data));

        System.out.println("Done");
    }

    // store a tax of state to tax_list
    public void parseTax(JSONObject data){
        String state = (String) data.get("state");
        double tax_rate = (double) data.get("tax_rate");
        JSONArray arr = (JSONArray) data.get("exempt_cat");
        String[] cat = new String[arr.size()];
        Helper_fun.JSONArray_to_list(arr).toArray(cat);

        Tax tax = new Tax(state, tax_rate, cat);
        tax_list.put(state, tax);
    }

    // print the tax detail for various states
    public void showTax(){
        tax_list.forEach((state,tax) -> tax.show());
    }

    // get the tax of a state
    public Tax getTax(String state){
        return tax_list.get(state);
    }


}
