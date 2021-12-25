/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package mygame;

import com.jme3.math.Vector3f;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * HeightsReaderTest is class that tests HeightsReader
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-10-03 
 */
public class PhysicsStateTest {

    /**
     * tests updateWalkDirection method.
     */
    @Test
    public void testUpdateWalkDirection() {
        PhysicsState instance = new PhysicsState();
        Vector3f vec1 = new Vector3f(1,1,1);
        Vector3f vec2 = new Vector3f(2,2,2);
        {
            Vector3f dir = instance.updateWalkDirection(true, false, false, false,
                                                        vec1, vec2);
            assertTrue(dir.equals(vec2));
        }
        {
            Vector3f dir = instance.updateWalkDirection(false, true, false, false,
                                                        vec1, vec2);
            assertTrue(dir.equals(vec2.negate()));
        }
        {
            Vector3f dir = instance.updateWalkDirection(false, false, true, false,
                                                        vec1, vec2);
            assertTrue(dir.equals(vec1));
        }
        {
            Vector3f dir = instance.updateWalkDirection(false, false, false, true,
                                                        vec1, vec2);
            assertTrue(dir.equals(vec1.negate()));
        }
    }
}
