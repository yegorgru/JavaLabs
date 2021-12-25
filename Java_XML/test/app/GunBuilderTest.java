package app;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GunBuilderTest {

    @Test
    void testBuilder() {
        GunBuilder builder = new GunBuilder();
        builder.startElement("Gun");
        builder.setCharacteristic("id", "gun1");
        builder.setCharacteristic("Model", "AK-47");
        builder.setCharacteristic("Range", "average");
        builder.finishElement("Gun");
        List<Gun> guns = builder.getCollection();
        assertEquals(1, guns.size());
        Gun ak = guns.get(0);
        assertEquals("gun1", ak.getId());
        assertEquals("AK-47", ak.getModel());
        Gun.PerformanceCharacteristics akCharacteristics = ak.getPerformanceCharacteristics();
        assertEquals("average", akCharacteristics.getRange());
    }
}