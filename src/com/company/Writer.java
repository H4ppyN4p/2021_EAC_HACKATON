package com.company;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Writer {
    int[] ints;
    String[] strings;

    public Writer(int[] ints, String[] strings){
        this.ints = ints;
        this.strings = strings;
    }

    public void run(){





        //Blank Workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create blank sheet
        XSSFSheet sheet = workbook.createSheet();


        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {ints[0], strings[0]});
        data.put("2", new Object[] {ints[1], strings[1]});
        data.put("3", new Object[] {ints[2], strings[2]});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr){
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        FileSystemView filesys = FileSystemView.getFileSystemView();

        try {
            FileOutputStream out = new FileOutputStream(filesys.getHomeDirectory() + "/EAC_HACKATON_2021/textures.xlsx");
            workbook.write(out);
            out.close();
            System.out.println(filesys.getHomeDirectory() + "/EAC_HACKATON_2021/textures.xlsx was written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
