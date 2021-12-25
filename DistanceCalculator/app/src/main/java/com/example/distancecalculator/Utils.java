package com.example.distancecalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * utils class that can round floating point numbers
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-12-05
 */
public class Utils {

    /** rounds floating point number
     *
     * @param value number to be rounded
     * @param places number of digits after floating point to be saved
     * @return result of rounding
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
