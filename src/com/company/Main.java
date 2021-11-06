package com.company;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main {

//Using https://howtodoinjava.com/java/library/readingwriting-excel-files-in-java-poi-tutorial/

    public static void main(String[] args) {
        FileSystemView filesys = FileSystemView.getFileSystemView();

        try {
            String[] strings = new String[3];
            double[] doubles = new double[3];

            int stringsArrayHandler = 0;
            int doublesArrayHandler = 0;
        //EAC_HACKATON_2021/textures.xlsx
    File file = new File(filesys.getHomeDirectory() + "/EAC_HACKATON_2021/textures.xlsx");
    FileInputStream fis = new FileInputStream(file);

            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            Iterator<Row> itr = sheet.iterator();

            while(itr.hasNext()){
                Row row = itr.next();
                Iterator<Cell> itrCell = row.cellIterator();

                while (itrCell.hasNext()){
                    Cell cell = itrCell.next();

                    switch (cell.getCellType())
                    {
                        case STRING:    //field that represents string cell type
                            System.out.println(cell.getStringCellValue());

                            strings[stringsArrayHandler] = cell.getStringCellValue();
                            stringsArrayHandler++;
                            break;
                        case NUMERIC:    //field that represents number cell type
                            System.out.println(cell.getNumericCellValue());

                            double doubleVariable = cell.getNumericCellValue();

                            doubles[doublesArrayHandler] = doubleVariable ;
                            doublesArrayHandler++;
                            break;
                        default:
                    }
                }
            }

            System.out.println();
            System.out.println("strings");
            for (String string: strings){
                System.out.println(string);
            }

            System.out.println();
            System.out.println("doubles");
            for (double doubleValues: doubles){
                System.out.println(doubleValues);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
