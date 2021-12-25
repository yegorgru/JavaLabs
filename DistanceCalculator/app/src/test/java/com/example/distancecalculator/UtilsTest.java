package com.example.distancecalculator;

import junit.framework.TestCase;

/**
 * tests Utils class
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-12-05
 */
public class UtilsTest extends TestCase {

    /** tests round method of Utils
     */
    public void testRound() {
        assertEquals(Utils.round(1.211111, 1), 1.2);
        assertEquals(Utils.round(1.6666, 2), 1.67);
        assertEquals(Utils.round(9.9999, 0), 10.0);
    }
}