package ShoppingCartManager;

import ProductManager.ProductManager;
import TaxManager.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import utils.HelperFunction;

import java.math.BigDecimal;
import java.util.Iterator;

public class ShoppingCartManager {
    private final TaxManager taxManager;
    private Tax state;
    private final ProductManager productManager;
    private Receipt receipt;

    public ShoppingCartManager(String path){
        this.taxManager = new TaxManager();
        this.productManager = new ProductManager();
        getTransactionRecord(path+".xlsx");
        receiptProducer();
        printReceipt();
    }

    // This function get the cell value from xlsx and record the products and tax state
    public void getTransactionRecord(String path){
        System.out.print("loading transaction in "+path+"...");
        XSSFSheet sheet = HelperFunction.excelReader(path);
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); //skip header
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            cellsToProduct(cellIterator);
        }

        System.out.println("Done");
    }

    // Match each row of transaction (excel) to a product, then handled by productManager
    public void cellsToProduct(Iterator<Cell> cellIterator){
        String taxState = null;
        String product = null;
        Object res;
        BigDecimal price = BigDecimal.valueOf(0);
        int qty = 0;
        while (cellIterator.hasNext()){
            Cell cell = cellIterator.next();
            int columnIndex = cell.getColumnIndex();
            switch (columnIndex){
                case 0:
                    if(taxState != null)
                        break;
                    taxState = (String) HelperFunction.getCellValue(cell);
                    this.state = taxManager.getTax(taxState);
                    if(state == null)
                        throw new RuntimeException("Unsupported state: "+taxState);
                    break;
                case 1:
                    product = (String) HelperFunction.getCellValue(cell);
                    break;
                case 2:
                    res = HelperFunction.getCellValue(cell);
                    price = res!=null? BigDecimal.valueOf((double) res) : price;
                    break;
                case 3:
                    res = HelperFunction.getCellValue(cell);
                    qty = res!=null? (int)((double) res) : qty;
                    break;
            }
        }
        // store the product detail by productManager
        productManager.addProduct(product, price, qty);

    }

    // Create the receipt and all required statistics
    public void receiptProducer(){
        System.out.print("producing receipt"+"...");
        BigDecimal subtotal = productManager.calSubTotal();
        BigDecimal tax = productManager.calTax(state);
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
