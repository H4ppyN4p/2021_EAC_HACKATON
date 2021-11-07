package com.company;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class Reader {
    String[] strings = new String[3];
    double[] doubles = new double[3];

    public void run(String string) {
        FileSystemView filesys = FileSystemView.getFileSystemView();
        File file = new File(filesys.getHomeDirectory() + string);


        Boolean fileExists = file.exists();


        if (fileExists == true) {
          readFile(file);
        } else if (fileExists == false) {
            System.out.println("that did not exist");
        }

    }

    public void readFile(File file){
        try {

            int stringsArrayHandler = 0;
            int doublesArrayHandler = 0;
            //EAC_HACKATON_2021/textures.xlsx
            FileInputStream fis = new FileInputStream(file);

            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            Iterator<Row> itr = sheet.iterator();

            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> itrCell = row.cellIterator();

                while (itrCell.hasNext()) {
                    Cell cell = itrCell.next();

                    System.out.println(cell.getCellType());
                    switch (cell.getCellType()) {
                        case STRING:    //field that represents string cell type

                            strings[stringsArrayHandler] = cell.getStringCellValue();
                            stringsArrayHandler++;
                            break;
                        case NUMERIC:    //field that represents number cell type

                            double doubleVariable = cell.getNumericCellValue();

                            doubles[doublesArrayHandler] = doubleVariable;
                            doublesArrayHandler++;
                            break;
                        default:
                    }


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getStrings() {
        return strings;
    }

    public double[] getDoubles() {
        return doubles;
    }

}
