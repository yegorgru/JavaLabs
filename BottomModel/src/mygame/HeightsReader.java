/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package mygame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * HeightsReader is class that reads height points from .xlsx file
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-10-03
 * @author Yehor Hrushevyy
 */
public class HeightsReader {
    
    /**
     * @param path path for xlsx file to read
     * @param soundSpeed speed of sound
     * @return array of height points
     */
    public static ArrayList<HeightPoint> getHeights(String path, 
                                                    double soundSpeed) {
        ArrayList<HeightPoint> result = readExcel(path);
        return calulateHeight(result, soundSpeed);
    }
    
    /** 
     * @param path path for xlsx file to read
     * @return array of height points, where height value represents time of radio signal
     */
    @SuppressWarnings("empty-statement")
    public static ArrayList<HeightPoint> readExcel(String path) {
        ArrayList<HeightPoint> result = new ArrayList<>();
        try {  
            File file = new File(path);  
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);   
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            if (itr.hasNext()) {
                itr.next();
            }
            while (itr.hasNext())                 
            {  
                Row row = itr.next();  
                Iterator<Cell> cellIterator = row.cellIterator(); 
                Cell cell = cellIterator.next();
                int x = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                int y = (int) cell.getNumericCellValue();
                cell = cellIterator.next();
                double time = cell.getNumericCellValue();
                result.add(new HeightPoint(x,y,time));
            }  
            return result;
        } catch(IOException e) {  
            return new ArrayList<>();
        }  
    }
    
    /** 
     * @param timeArray array of height points, where height value represents time of radio signal
     * @param soundSpeed speed of sound
     * @return array of height points, where height value represents height on map
     */
    public static ArrayList<HeightPoint> calulateHeight(ArrayList<HeightPoint> timeArray,
                                                        double soundSpeed) {
        for(HeightPoint point : timeArray) {
            if (point.getX() < 1 || point.getX() > 510 
                    || point.getZ() < 1 || point.getZ() > 510 || point.getHeight() < 0) {
                point.setValid(false);
                continue;
            }
            double value = 254 - point.getHeight() * soundSpeed / 2;
            point.setHeight(value < 0 ? 0 : value);
        }
        return timeArray;
    }
}
