package com.example.distancecalculator;

/**
 * class that processes acceleration and updates speed and distance
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-12-05
 */
public class Tracker {
    private double[] speed = new double[]{0.0, 0.0, 0.0};
    private double distance = 0.0;

    /** updates speed (by 3 axises) and distance by acceleration values
     *
     * @param acceleration array of accelerations by 3 axes: x, y, z
     * @param dt time frame
     */
    public void updateTrackedValues(float[] acceleration, double dt) {
        double[] prevSpeed = speed.clone();

        double newDistance = 0.0;

        for(int i = 0; i < 3; i++) {
            speed[i] = Utils.round(speed[i] + acceleration[i] * dt,3);
            if(Math.abs(speed[i]) - Math.abs(prevSpeed[i]) < 0.001) {
                speed[i] = 0.0;
            }
            newDistance += Math.pow((speed[i] + prevSpeed[i]) / 2.0 * dt, 2);
        }

        newDistance = Math.sqrt(newDistance);

        distance += newDistance;
    }

    /** zeroes the speed and distance
     */
    public void restart() {
        speed = new double[]{0.0, 0.0, 0.0};
        distance = 0.0;
    }

    /** speed getter by 3 axes
     */
    public double[] getSpeed() {
        return speed;
    }

    /** distance getter
     */
    public double getDistance() {
        return distance;
    }
}
