package com.excel.helper;

import com.excel.entity.Category;
import com.excel.entity.Product;
import org.apache.catalina.manager.JspHelper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {
    // check file is of excel or not
    public static boolean checkExcelFormat(MultipartFile file){
        String contentType = file.getContentType();
        if(contentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return true;
        }
        else{
            return false;
        }
    }
    // convert excel to list of product
    public static List<Product> convertExcelToListOfProduct(InputStream is){
        List<Product> list = new ArrayList<>();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("data");
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()){
                Row row = iterator.next();

                if (rowNumber == 0)
                {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                Product p = new Product();
                while (cells.hasNext()){
                    Cell cell = cells.next();

                    switch (cid){
                        case 0:
                            p.setProductId((int)cell.getNumericCellValue());
                            break;
                        case 1:
                            p.setProductName(cell.getStringCellValue());
                            break;
                        case 2:
                            p.setProductDesc(cell.getStringCellValue());
                            break;
                        case 3:
                            p.setProductPrice(String.valueOf(cell.getNumericCellValue()));
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(p);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //store the haeder name of excel sheet
    public static String[] HEADERS = {
            "id","title","description","coverImage"
    };

    public static String SHEET_NAME = "category_data";
    public static ByteArrayInputStream dataToExcel(List<Category> list) throws IOException{
        // create WorkBook
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            // create sheet
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            //create row : header row
            Row row = sheet.createRow(0);

            //create colum header
            for (int i=0;i< HEADERS.length;i++)
            {
                row.createCell(i).setCellValue(HEADERS[i]);
            }
            // value row
            int rowIndex = 1;
            for (Category c : list){
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;

                dataRow.createCell(0).setCellValue(c.getCategoryId());
                dataRow.createCell(1).setCellValue(c.getTitle());
                dataRow.createCell(2).setCellValue(c.getDescription());
                dataRow.createCell(3).setCellValue(c.getCoverImage());
            }
            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());

        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("failed to import data to excel");
        }
        finally {
            workbook.close();
            out.close();
        }
        return null;
    }
}
