/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package mygame;

/**
 * HeightPoint is class that represents single point on heightmap.
 *
 * @version 1.10 03 Oct 2021
 * @author Yehor Hrushevyy
 */
public class HeightPoint {
    
    private final int x;
    private final int z;
    private double height;
    private boolean valid;
    
    /** Creates height point with specified coordinates and height
     * @param x X coordinate
     * @param z Z coordinate
     * @param height height of map point
     */
    public HeightPoint(int x, int z, double height) {
        this.x = x;
        this.z = z;
        this.height = height;
        this.valid = true;
    }
    
    /** gets X coordinate
     * @return int that represents x coordinate
     */
    public int getX() {
        return x;
    }
    
    /** gets Z coordinate
    * @return int that represents z coordinate
    */
    public int getZ() {
        return z;
    }
    
    /** gets Height
     * @return double that represents height value
     */
    public double getHeight() {
        return height;
    }
    
    /** sets height
     * @param value height value to be set
     */
    public void setHeight(double value) {
        height = value;
    }
    
    /** gets valid
     * @return boolean if map point can be used
     */
    public boolean isValid() {
        return valid;
    }
    
    /** sets if map point can be used
     * @param value if map point is valid
     */
    public void setValid(boolean value) {
        valid = value;
    }
}
