/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package app;

import java.util.ArrayList;
import java.util.List;

/**
 * Main is entry point class
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-11-01
 */
class Main {

    /** application entry point. Read guns from 3 xml sources
     *
     * @param args arguments from command prompt
     */
    public static void main(String[] args) {
        try {
            GunBuilder builder = new GunBuilder();

            ParserDOM parserDOM = new ParserDOM();
            parserDOM.setSchema("resources/collection.xsd");
            parserDOM.setSource("resources/gunSource1.xml");
            if(!parserDOM.isValid()){
                throw new Exception("gunSource1.xml is not valid");
            }

            parserDOM.parse(builder);

            ParserSAX parserSAX = new ParserSAX();
            parserSAX.setSchema("resources/collection.xsd");
            parserSAX.setSource("resources/gunSource2.xml");
            if(!parserSAX.isValid()){
                throw new Exception("gunSource2.xml is not valid");
            }
            parserSAX.parse(builder);

            ParserStAX parserStAX = new ParserStAX();
            parserStAX.setSchema("resources/collection.xsd");
            parserStAX.setSource("resources/gunSource3.xml");
            if(!parserStAX.isValid()){
                throw new Exception("gunSource3.xml is not valid");
            }
            parserStAX.parse(builder);

            List<Gun> guns = builder.getCollection();

            guns.sort(new Gun.IdComparator());

            System.out.println("Gun collection: ");
            for(Gun gun : guns) {
                printGun(gun);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    private static void printGun(Gun gun) {
        System.out.println("Id: " + gun.getId());
        System.out.println("\tModel: " + gun.getModel());
        System.out.println("\tHandy: " + gun.getHandy());
        System.out.println("\tOrigin: " + gun.getOrigin());
        System.out.println("\tMaterial: " + gun.getMaterial());
        System.out.println("\tPerformance characteristics: ");
        Gun.PerformanceCharacteristics characteristics = gun.getPerformanceCharacteristics();
        System.out.println("\t\tRange: " + characteristics.getRange());
        System.out.println("\t\tSighting range: " + characteristics.getSightingRange());
        System.out.println("\t\tClip available: " + characteristics.isClip());
        System.out.println("\t\tOptics available: " + characteristics.isOptics());
    }
}
