/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package mygame;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * HeightPointTest is class that tests HeightPoint
 *    
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-10-03 
 */
public class HeightPointTest {

    /* tests getX method */
    @Test
    public void testGetX() {
        HeightPoint instance = new HeightPoint(1,2,3.0);
        assertEquals(instance.getX(), 1);
        assertTrue(instance.isValid());
    }

    /**
     * tests getZ method
     */
    @Test
    public void testGetZ() {
        HeightPoint instance = new HeightPoint(1,2,3.0);
        assertEquals(instance.getZ(), 2);
        assertTrue(instance.isValid());
    }

    /* tests getHeight method */
    @Test
    public void testGetHeight() {
        HeightPoint instance = new HeightPoint(1,2,3.0);
        assertEquals(3.0, instance.getHeight(), 0.001d);
    }

    /* tests setHeight method */
    @Test
    public void testSetHeight() {
        HeightPoint instance = new HeightPoint(1,2,3.0);
        double value = 5.0;
        instance.setHeight(value);
        assertEquals(value, instance.getHeight(), 0.001d);
    }

    /* tests isValid method */
    @Test
    public void testIsValid() {
        HeightPoint instance = new HeightPoint(1,2,3.0);
        assertTrue(instance.isValid());
    }
    
    /* tests setValid method */
    @Test
    public void testSetValid() {
        HeightPoint instance = new HeightPoint(1,2,3.0);
        instance.setValid(false);
        assertFalse(instance.isValid());
    }
}
