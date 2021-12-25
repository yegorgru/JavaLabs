/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package mygame;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * HeightsReaderTest is class that tests HeightsReader
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-10-03 
 */
public class HeightsReaderTest {

    /* tests getHeights method,
     * reads height points from test.xlsx 
     */ 
    @Test
    public void testGetHeights() {
        String path = System.getProperty("user.dir");
        path += "\\assets\\ExcelTables\\test.xlsx";
        double soundSpeed = 10d;
        ArrayList<HeightPoint> expResult = new ArrayList<>();
        expResult.add(new HeightPoint(1, 2, 229d));
        expResult.add(new HeightPoint(3, 4, 238d));
        expResult.add(new HeightPoint(6, 7, 250d));
        ArrayList<HeightPoint> result = HeightsReader.getHeights(path, soundSpeed);
        assertEqualsHeightPointArrays(expResult, result);
    }

    /* tests readExcel method,
     * reads height points from test.xlsx 
     */ 
    @Test
    public void testReadExcel() {
        System.out.println("readExcel");
        ArrayList<HeightPoint> expResult = new ArrayList<>();
        expResult.add(new HeightPoint(1, 2, 5.0d));
        expResult.add(new HeightPoint(3, 4, 3.2d));
        expResult.add(new HeightPoint(6, 7, 0.8d));
        String path = System.getProperty("user.dir");
        path += "\\assets\\ExcelTables\\test.xlsx";
        ArrayList<HeightPoint> result = HeightsReader.readExcel(path);
        assertEqualsHeightPointArrays(expResult, result);
    }

    /* tests calculateHeight method */ 
    @Test
    public void testCalulateHeight() {
        ArrayList<HeightPoint> expResult = new ArrayList<>();
        expResult.add(new HeightPoint(1, 2, 241.5d));
        expResult.add(new HeightPoint(3, 4, 246d));
        expResult.add(new HeightPoint(6, 7, 252d));
        testCalculateHeight(expResult, 5d);
    }
    
    /* tests calculateHeight method with too bif speed of sound */ 
    @Test
    public void testCalulateHeightTooBigSpeed() {
        ArrayList<HeightPoint> expResult = new ArrayList<>();
        expResult.add(new HeightPoint(1, 2, 0d));
        expResult.add(new HeightPoint(3, 4, 0d));
        expResult.add(new HeightPoint(6, 7, 0d));
        testCalculateHeight(expResult, 1000d);
    }
    
    /* test calculate height helper method
     * @param expResult expected array of height points
     * @param soundSpeed speed of sound
     */ 
    private void testCalculateHeight(ArrayList<HeightPoint> expResult, 
                                     double soundSpeed) {
        String path = System.getProperty("user.dir");
        path += "\\assets\\ExcelTables\\test.xlsx";
        ArrayList<HeightPoint> timeArray = HeightsReader.readExcel(path);
        ArrayList<HeightPoint> result = HeightsReader.calulateHeight(timeArray, soundSpeed);
        assertEqualsHeightPointArrays(expResult, result);
    }
    
    /* helps compare arrays of height points
     * @param lhs first array to compare
     * @param rhs second array to compare
     */ 
    private void assertEqualsHeightPointArrays(ArrayList<HeightPoint> lhs,
                                               ArrayList<HeightPoint> rhs){
        assertEquals(lhs.size(), rhs.size());
        for(int i = 0; i < lhs.size(); i++){
            assertEquals(lhs.get(i).getX(), rhs.get(i).getX());
            assertEquals(lhs.get(i).getZ(), rhs.get(i).getZ());
            assertEquals(lhs.get(i).getHeight(), rhs.get(i).getHeight(), 0.001);
        }
    }
}
