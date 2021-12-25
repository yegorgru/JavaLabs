/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package app;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType
 *   &lt;complexContent
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"
 *       &lt;sequence
 *         &lt;element name="Model" type="{http://www.w3.org/2001/XMLSchema}string"/
 *         &lt;element name="Handy"
 *           &lt;simpleType
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"
 *               &lt;enumeration value="One-handed"/
 *               &lt;enumeration value="Two-handed"/
 *             &lt;/restriction
 *           &lt;/simpleType
 *         &lt;/element
 *         &lt;element name="Origin" type="{http://www.w3.org/2001/XMLSchema}string"/
 *         &lt;element name="PerformanceCharacteristics"
 *           &lt;complexType
 *             &lt;complexContent
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"
 *                 &lt;sequence
 *                   &lt;element name="Range"
 *                     &lt;simpleType
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"
 *                         &lt;pattern value="close|average|long"/
 *                       &lt;/restriction
 *                     &lt;/simpleType
 *                   &lt;/element
 *                   &lt;element name="SightingRange"
 *                     &lt;simpleType
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"
 *                         &lt;minInclusive value="1"/
 *                         &lt;maxInclusive value="20000"/
 *                       &lt;/restriction
 *                     &lt;/simpleType
 *                   &lt;/element
 *                   &lt;element name="Clip" type="{http://www.w3.org/2001/XMLSchema}boolean"/
 *                   &lt;element name="Optics" type="{http://www.w3.org/2001/XMLSchema}boolean"/
 *                 &lt;/sequence
 *               &lt;/restriction
 *             &lt;/complexContent
 *           &lt;/complexType
 *         &lt;/element
 *         &lt;element name="Material" type="{http://www.w3.org/2001/XMLSchema}string"/
 *       &lt;/sequence
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /
 *     &lt;/restriction
 *   &lt;/complexContent
 * &lt;/complexType
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "model",
    "handy",
    "origin",
    "performanceCharacteristics",
    "material"
})
@XmlRootElement(name = "Gun")
public class Gun {

    @XmlElement(name = "Model", required = true)
    protected String model;
    @XmlElement(name = "Handy", required = true)
    protected String handy;
    @XmlElement(name = "Origin", required = true)
    protected String origin;
    @XmlElement(name = "PerformanceCharacteristics", required = true)
    protected Gun.PerformanceCharacteristics performanceCharacteristics;
    @XmlElement(name = "Material", required = true)
    protected String material;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = String.valueOf(value);
    }

    /**
     * Gets the value of the handy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandy() {
        return handy;
    }

    /**
     * Sets the value of the handy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandy(String value) {
        this.handy = String.valueOf(value);
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = String.valueOf(value);
    }

    /**
     * Gets the value of the performanceCharacteristics property.
     * 
     * @return
     *     possible object is
     *     {@link Gun.PerformanceCharacteristics }
     *     
     */
    public Gun.PerformanceCharacteristics getPerformanceCharacteristics() {
        return performanceCharacteristics;
    }

    /**
     * Sets the value of the performanceCharacteristics property.
     * 
     * @param value
     *     allowed object is
     *     {@link Gun.PerformanceCharacteristics }
     *     
     */
    public void setPerformanceCharacteristics(Gun.PerformanceCharacteristics value) {
        this.performanceCharacteristics = value;
    }

    /**
     * Gets the value of the material property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the value of the material property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterial(String value) {
        this.material = String.valueOf(value);
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = String.valueOf(value);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType
     *   &lt;complexContent
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"
     *       &lt;sequence
     *         &lt;element name="Range"
     *           &lt;simpleType
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"
     *               &lt;pattern value="close|average|long"/
     *             &lt;/restriction
     *           &lt;/simpleType
     *         &lt;/element
     *         &lt;element name="SightingRange"
     *           &lt;simpleType
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"
     *               &lt;minInclusive value="1"/
     *               &lt;maxInclusive value="20000"/
     *             &lt;/restriction
     *           &lt;/simpleType
     *         &lt;/element
     *         &lt;element name="Clip" type="{http://www.w3.org/2001/XMLSchema}boolean"/
     *         &lt;element name="Optics" type="{http://www.w3.org/2001/XMLSchema}boolean"/
     *       &lt;/sequence
     *     &lt;/restriction
     *   &lt;/complexContent
     * &lt;/complexType
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "range",
        "sightingRange",
        "clip",
        "optics"
    })
    public static class PerformanceCharacteristics {

        @XmlElement(name = "Range", required = true)
        protected String range;
        @XmlElement(name = "SightingRange")
        protected int sightingRange;
        @XmlElement(name = "Clip")
        protected boolean clip;
        @XmlElement(name = "Optics")
        protected boolean optics;

        List<String> getCharacteristicNames() {
            List<String> result = new ArrayList<>();
            result.add("Range");
            result.add("SightingRange");
            result.add("Clip");
            result.add("Optics");
            return result;
        }

        /**
         * Gets the value of the range property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRange() {
            return range;
        }

        /**
         * Sets the value of the range property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRange(String value) {
            this.range = value;
        }

        /**
         * Gets the value of the sightingRange property.
         *
         * @return sighting range
         */
        public int getSightingRange() {
            return sightingRange;
        }

        /**
         * Sets the value of the sightingRange property.
         *
         * @param value sighting range to set
         */
        public void setSightingRange(int value) {
            this.sightingRange = value;
        }

        /**
         * Gets the value of the clip property.
         *
         * @return boolean whether Gun has clip
         */
        public boolean isClip() {
            return clip;
        }

        /**
         * Sets the value of the clip property.
         *
         * @param value whether Gun has clip to set
         */
        public void setClip(boolean value) {
            this.clip = value;
        }

        /**
         * Gets the value of the optics property.
         *
         * @return boolean whether Gun has optics
         */
        public boolean isOptics() {
            return optics;
        }

        /**
         * Sets the value of the optics property.
         *
         * @param value whether Gun has optics to set
         */
        public void setOptics(boolean value) {
            this.optics = value;
        }

    }

    /**
     * Comparator class to compare Guns
     *
     * @author  Yehor Hrushevyy
     * @version 1.0 2021-11-01
     */
    public static class IdComparator implements Comparator<Gun> {

        /** compares Guns by id
         *
         * @param g1 lhs Gun
         * @param g2 rhs Gun
         * @return int result of Guns id comparison
         */
        @Override
        public int compare(Gun g1, Gun g2) {
            return g1.getId().compareTo(g2.getId());
        }
    }
}
