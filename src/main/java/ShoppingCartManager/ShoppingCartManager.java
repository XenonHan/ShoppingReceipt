package ShoppingCartManager;

import ProductManager.ProductManager;
import TaxManager.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import utils.Helper_fun;

import java.util.Iterator;

public class ShoppingCartManager {
    private final TaxManager tax_manager;
    private Tax state;
    private final ProductManager productManager;
    private Receipt receipt;

    public ShoppingCartManager(String path){
        this.tax_manager = new TaxManager();
        this.productManager = new ProductManager();
        getTransactionRecord(path+".xlsx");
        receiptProducer();
        printReceipt();
    }

    // This function get the cell value from xlsx and record the products and tax state
    public void getTransactionRecord(String path){
        System.out.print("loading transaction in "+path+"...");
        XSSFSheet sheet = Helper_fun.excelReader(path);
        Iterator<Row> row_it = sheet.iterator();
        String tax_state = null;
        String product = null;
        Object res;
        double price = 0;
        int qty = 0;
        row_it.next(); //skip header
        while(row_it.hasNext()){
            Row row = row_it.next();
            Iterator<Cell> cell_it = row.cellIterator();
            while (cell_it.hasNext()){
                Cell cell = cell_it.next();
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex){
                    case 0:
                        if(tax_state != null)
                            break;
                        tax_state = (String) Helper_fun.getCellValue(cell);
                        this.state = tax_manager.getTax(tax_state);
                        if(state == null)
                            throw new RuntimeException("Unsupported state: "+tax_state);
                        break;
                    case 1:
                        product = (String) Helper_fun.getCellValue(cell);
                        break;
                    case 2:
                        res = Helper_fun.getCellValue(cell);
                        price = res!=null? (double) res: 0;
                        break;
                    case 3:
                        res = Helper_fun.getCellValue(cell);
                        qty = res!=null? (int)((double) res): 0;
                        break;
                }
            }
            // store the product detail by productManager
            productManager.addProduct(product, price, qty);
        }

        System.out.println("Done");
    }

    // Create the receipt and all required statistics
    public void receiptProducer(){
        System.out.print("producing receipt"+"...");
        double subtotal = productManager.calSubTotal();
        double tax = productManager.calTax(state);
        this.receipt = new Receipt(subtotal, tax);
        System.out.println("Done");
    }

    //Print receipt
    public void printReceipt(){
        System.out.println("\n");
        String format = "%-20s%-20s%s%n";
        System.out.printf(format, "item", "price", "qty");
        System.out.print("\n");
        productManager.show();
        receipt.show();
    }

}
