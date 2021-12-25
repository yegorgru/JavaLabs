/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package app;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the app package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class GunFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: app
     *
     */
    public GunFactory() {
    }

    /**
     * Create an instance of {@link Gun }
     *
     * @param id id of created Gun
     * @param model model of created Gun
     * @param handy represents if created Gun is one-handed or two-handed
     * @param origin country where Gun is created
     * @param material material of created Gun
     * @return created Gun
     */
    public Gun createGun(String id, String model, String handy, String origin, String material) {
        Gun gun = new Gun();
        gun.setId(id);
        gun.setModel(model);
        gun.setHandy(handy);
        gun.setOrigin(origin);
        gun.setMaterial(material);
        return gun;
    }

    /**
     * Create an instance of {@link Gun.PerformanceCharacteristics }
     *
     * @param range range of Gun
     * @param sightingRange sighting range of Gun
     * @param clip boolean if Gun has clip
     * @param optics boolean if Gun has optics
     * @return created Gun.PerformanceCharacteristics
     */
    public Gun.PerformanceCharacteristics createGunPerformanceCharacteristics(String range,
                                                                              int sightingRange,
                                                                              boolean clip,
                                                                              boolean optics) {
        Gun.PerformanceCharacteristics characteristics = new Gun.PerformanceCharacteristics();
        characteristics.setRange(range);
        characteristics.setSightingRange(sightingRange);
        characteristics.setClip(clip);
        characteristics.setOptics(optics);
        return characteristics;
    }
}
