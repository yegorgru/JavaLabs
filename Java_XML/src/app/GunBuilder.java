package app;

import java.util.ArrayList;
import java.util.List;

/**
 * processes attributes, tags, etc
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-11-01
 */
public class GunBuilder {
    Gun current;
    List<Gun> collection;

    /**
     * initializes collection
     */
    public GunBuilder() {
        collection = new ArrayList<>();
    }

    /**
     * collection getter
     */
    public List<Gun> getCollection() {
        return collection;
    }

    /**
     * Gets names of gun characteristics
     *
     * @return list of characteristic names
     */
    List<String> getCharacteristicNames() {
        List<String> result = new ArrayList<>();
        result.add("id");
        result.add("Model");
        result.add("Handy");
        result.add("Material");
        result.add("Origin");
        result.add("Range");
        result.add("SightingRange");
        result.add("Clip");
        result.add("Optics");
        return result;
    }

    /**
     * Gets names of gun characteristics
     *
     * @param characteristic name of characteristic to set
     * @param value value to set
     */
    public void setCharacteristic(String characteristic, String value) {
        switch (characteristic) {
            case "id":
                current.setId(value);
                break;
            case "Model":
                current.setModel(value);
                break;
            case "Handy":
                current.setHandy(value);
                break;
            case "Origin":
                current.setOrigin(value);
                break;
            case "Range":
                current.getPerformanceCharacteristics().setRange(value);
                break;
            case "SightingRange":
                current.getPerformanceCharacteristics().setSightingRange(Integer.parseInt(value));
                break;
            case "Clip":
                current.getPerformanceCharacteristics().setClip(Boolean.parseBoolean(value));
                break;
            case "Optics":
                current.getPerformanceCharacteristics().setOptics(Boolean.parseBoolean(value));
                break;
            case "Material":
                current.setMaterial(value);
                break;
        }
    }

    /**
     * Starts processing of element
     *
     * @param name name of element
     * @return boolean if name equals "Gun"
     */
    public boolean startElement(String name) {
        if(name.equals("Gun")) {
            current = new Gun();
            current.setPerformanceCharacteristics(new Gun.PerformanceCharacteristics());
            return true;
        }
        return false;
    }

    /**
     * Finishes processing of element
     *
     * @param name name of element
     * @return boolean if name equals "Gun"
     */
    public boolean finishElement(String name) {
        if(name.equals("Gun")) {
            collection.add(current);
            return true;
        }
        return false;
    }

    /**
     * @returns name of main element
     */
    public String getElementName() {
        return "Gun";
    }

    /**
     * @returns name attribute
     */
    public String getAttributeName() {
        return "id";
    }
}
