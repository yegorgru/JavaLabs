import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomClassLoaderTest {

    @Test
    public void checkLoadingClass(){
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class foundClass = customClassLoader.findClass("TestClass");
        assertEquals(foundClass.getName(), "TestClass");
    }

}