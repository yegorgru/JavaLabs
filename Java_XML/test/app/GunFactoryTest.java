/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class to test GunFactory class
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-11-01
 */
class GunFactoryTest {

    private GunFactory factory;

    /**
     * makes new factory before each test
     */
    @BeforeEach
    void setUp() {
         factory = new GunFactory();
    }

    /**
     * tests createGun method
     */
    @Test
    void createGun() {
        Gun gun = factory.createGun(
                "Gun","AK-47", "Two-handed", "USSR", "metal"
        );
        assertEquals("Gun", gun.getId());
        assertEquals("AK-47", gun.getModel());
        assertEquals("Two-handed", gun.getHandy());
        assertEquals("USSR", gun.getOrigin());
        assertEquals("metal", gun.getMaterial());
    }

    /**
     * tests createGunPerformanceCharacteristics method
     */
    @Test
    void createGunPerformanceCharacteristics() {
        Gun.PerformanceCharacteristics characteristics = factory.createGunPerformanceCharacteristics(
                "close",
                500,
                true,
                false
        );
        assertEquals("close", characteristics.getRange());
        assertEquals(500, characteristics.getSightingRange());
        assertTrue(characteristics.isClip());
        assertFalse(characteristics.isOptics());
    }
}