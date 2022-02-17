package TaxManager;
import config.env;
import utils.HelperFunction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;


// this class load the tax info of various states from Tax.json
public class TaxManager {
    private final HashMap<String, Tax> taxList;
    @SuppressWarnings("unchecked")
    public TaxManager(){
        taxList = new HashMap<>();
        System.out.print("loading tax data...");

        //load the tax.json
        JSONArray state_arr = HelperFunction.load_json(env.config_path+"/tax.json");
        state_arr.forEach(data -> parseTax((JSONObject) data));

        System.out.println("Done");
    }

    // store a tax of state to tax_list
    public void parseTax(JSONObject data){
        String state = (String) data.get("state");
        BigDecimal taxRate = BigDecimal.valueOf((double) data.get("tax_rate"));
        JSONArray arr = (JSONArray) data.get("exempt_cat");
        String[] cat = new String[arr.size()];
        HelperFunction.JSONArray_to_list(arr).toArray(cat);

        Tax tax = new Tax(state, taxRate, cat);
        taxList.put(state, tax);
    }

    // print the tax detail for various states
    public void showTax(){
        taxList.forEach((state,tax) -> tax.show());
    }

    // get the tax of a state
    public Tax getTax(String state){
        return taxList.get(state);
    }


}
