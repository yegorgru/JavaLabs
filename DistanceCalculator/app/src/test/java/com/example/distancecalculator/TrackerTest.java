package com.example.distancecalculator;

import junit.framework.TestCase;

/**
 * tests Tracker class
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-12-05
 */
public class TrackerTest extends TestCase {

    /** tests updateTrackedValues and restart methods of Tracker
     */
    public void testTracker() {
        Tracker tracker = new Tracker();
        for(double value : tracker.getSpeed()) {
            assertEquals(value, 0.0);
        }
        assertEquals(tracker.getDistance(), 0.0);

        tracker.updateTrackedValues(new float[]{10, 20, 30}, 0.1);
        for(int i = 0; i < 3; i++) {
            assertEquals(tracker.getSpeed()[i], (double)i+1);
        }
        double expectedDistance = Math.sqrt(
            Math.pow((0.5 * 0.1), 2) + Math.pow((1 * 0.1), 2) + Math.pow((1.5 * 0.1), 2)
        );
        assertEquals(expectedDistance, tracker.getDistance());

        tracker.updateTrackedValues(new float[]{10, 10, 10}, 0.1);
        for(int i = 0; i < 3; i++) {
            assertEquals(tracker.getSpeed()[i], (double)i+2);
        }
        expectedDistance += Math.sqrt(
                Math.pow((1.5 * 0.1), 2) + Math.pow((2.5 * 0.1), 2) + Math.pow((3.5 * 0.1), 2)
        );
        assertEquals(expectedDistance, tracker.getDistance());
    }
}