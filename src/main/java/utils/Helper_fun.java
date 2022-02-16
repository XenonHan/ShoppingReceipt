package utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

// This class store the helper functions
public class Helper_fun {

    // Convert JSONArray to list
    public static <T> List<T> JSONArray_to_list(JSONArray arr){
        List<T> list = new ArrayList<T>();
        for(int i =0; i< arr.size(); i ++){
            list.add((T)arr.get(i));
        }
        return list;
    }

    //load .json
    public static JSONArray load_json(String path){
        JSONParser parser = new JSONParser();
        JSONArray arr = null;
        try (FileReader json = new FileReader(path)){
            Object obj = parser.parse(json);
            arr = (JSONArray) obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return arr;
    }

    // load .xlsx
    public static XSSFSheet excelReader(String path){
        XSSFSheet sheet = null;
        try{
            File file = new File(path);
            FileInputStream files_stream = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(files_stream);
            sheet = wb.getSheetAt(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    // get xlsx cell value
    public static Object getCellValue(Cell cell){
        switch (cell.getCellType()){
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
    }

    // roundup the value to nearest 0.05
    public static double myRound(double value){
        return Math.ceil(value * 20)/20.0;
    }


}
