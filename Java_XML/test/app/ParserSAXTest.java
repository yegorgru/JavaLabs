/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class to test ParserSAX class
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-11-01
 */
class ParserSAXTest {
    private ParserSAX parser;

    /**
     * makes new ParserSAX before each test
     */
    @BeforeEach
    void setUp() {
        parser = new ParserSAX();
    }

    /**
     * tests setSchema method
     */
    @Test
    void setSchema() {
        assertThrows(Exception.class, () -> parser.setSchema("resources/invalid_path.xsd"));
        assertDoesNotThrow(() -> parser.setSchema("resources/collection.xsd"));
    }

    /**
     * tests setSource method
     */
    @Test
    void setSource() {
        assertThrows(Exception.class, () -> parser.setSource("resources/invalid_path.xml"));
        assertDoesNotThrow(() -> parser.setSource("resources/gunSource1.xml"));
    }

    /**
     * tests parseGuns method
     */
    @Test
    void parse() {
        try{
            parser.setSchema("resources/collection.xsd");
            parser.setSource("resources/testGuns.xml");
            GunBuilder builder = new GunBuilder();
            parser.parse(builder);
            List<Gun> guns = builder.getCollection();
            assertEquals(2, guns.size());

            Gun ak = guns.get(0);
            assertEquals("gun1", ak.getId());
            assertEquals("AK-47", ak.getModel());
            assertEquals("Two-handed", ak.getHandy());
            assertEquals("USSR", ak.getOrigin());
            assertEquals("metal", ak.getMaterial());
            Gun.PerformanceCharacteristics akCharacteristics = ak.getPerformanceCharacteristics();
            assertEquals("average", akCharacteristics.getRange());
            assertEquals(500, akCharacteristics.getSightingRange());
            assertTrue(akCharacteristics.isClip());
            assertFalse(akCharacteristics.isOptics());

            Gun m16 = guns.get(1);
            assertEquals("gun2", m16.getId());
            assertEquals("M16/M4/AR-15", m16.getModel());
            assertEquals("One-handed", m16.getHandy());
            assertEquals("USA", m16.getOrigin());
            assertEquals("plastic", m16.getMaterial());
            Gun.PerformanceCharacteristics m16Characteristics = m16.getPerformanceCharacteristics();
            assertEquals("long", m16Characteristics.getRange());
            assertEquals(600, m16Characteristics.getSightingRange());
            assertFalse(m16Characteristics.isClip());
            assertTrue(m16Characteristics.isOptics());
        } catch(Exception e) {
            fail();
        }
    }

    /**
     * tests isValid method
     */
    @Test
    void isValid() {
        try{
            parser.setSchema("resources/collection.xsd");
            parser.setSource("resources/testGuns.xml");
            assertTrue(parser.isValid());
            parser.setSource("resources/testGunsInvalid.xml");
            assertFalse(parser.isValid());
        } catch(Exception e) {
            fail();
        }
    }
}