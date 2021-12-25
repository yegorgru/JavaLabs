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
 * class to test Gun class and nested
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-11-01
 */
class GunTest {

    private Gun gun;
    private Gun.PerformanceCharacteristics characteristics;

    /**
     * makes new Gun and Gun.PerformanceCharacteristics before each test
     */
    @BeforeEach
    void setUp() {
        gun = new Gun();
        characteristics = new Gun.PerformanceCharacteristics();
    }

    /**
     * tests get/setModel methods
     */
    @Test
    void model() {
        gun.setModel("AK-47");
        assertEquals("AK-47", gun.getModel());
    }

    /**
     * tests get/setHandy methods
     */
    @Test
    void handy() {
        gun.setHandy("One-handed");
        assertEquals("One-handed", gun.getHandy());
    }

    /**
     * tests get/setOrigin methods
     */
    @Test
    void origin() {
        gun.setOrigin("Ukraine");
        assertEquals("Ukraine", gun.getOrigin());
    }

    /**
     * tests get/setPerformanceCharacteristics methods
     */
    @Test
    void performanceCharacteristics() {
        GunFactory factory = new GunFactory();
        characteristics = factory.createGunPerformanceCharacteristics(
                "close", 200, false, true
        );
        gun.setPerformanceCharacteristics(characteristics);
        assertEquals("close", gun.getPerformanceCharacteristics().getRange());
        assertEquals(200, gun.getPerformanceCharacteristics().getSightingRange());
        assertFalse(gun.getPerformanceCharacteristics().isClip());
        assertTrue(gun.getPerformanceCharacteristics().isOptics());
    }

    /**
     * tests get/setMaterial methods
     */
    @Test
    void material() {
        gun.setMaterial("metal");
        assertEquals("metal", gun.getMaterial());
    }

    /**
     * tests get/setId methods
     */
    @Test
    void id() {
        gun.setId("gun1");
        assertEquals("gun1", gun.getId());
    }

    /**
     * tests get/setRange methods
     */
    @Test
    void range() {
        characteristics.setRange("long");
        assertEquals("long", characteristics.getRange());
    }

    /**
     * tests get/setSightingRange methods
     */
    @Test
    void sightingRange() {
        characteristics.setSightingRange(1000);
        assertEquals(1000, characteristics.getSightingRange());
    }

    /**
     * tests setClip and isClip methods
     */
    @Test
    void clip() {
        assertFalse(characteristics.isClip());
        characteristics.setClip(true);
        assertTrue(characteristics.isClip());
    }

    /**
     * tests setOptics and isOptics methods
     */
    @Test
    void optics() {
        assertFalse(characteristics.isOptics());
        characteristics.setOptics(true);
        assertTrue(characteristics.isOptics());
    }

    /**
     * tests correct comparison of Gun.IdComparator
     */
    @Test
    void comparator() {
        Gun g1 = new Gun();
        g1.setId("a");
        Gun g2 = new Gun();
        g2.setId("b");
        Gun g3 = new Gun();
        g3.setId("c");
        Gun.IdComparator comparator = new Gun.IdComparator();
        assertTrue(comparator.compare(g2, g1) > 0);
        assertTrue(comparator.compare(g2, g3) < 0);
    }
}