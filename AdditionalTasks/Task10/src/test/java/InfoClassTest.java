import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InfoClassTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testTestClss() {
        InfoClass infoClass = new InfoClass("TestClass");
        infoClass.showClassInfo();
        assertEquals("TestClass:\r\n" +
                "Modifiers of the class: public class\r\n" +
                "Doesn't extend any class.\r\n" +
                "Does not implement any interfaces.\r\n" +
                "No fields. \r\n" +
                "Methods: \r\n" +
                "   public void test\n" +
                "      no parameters\r\n", outContent.toString());
    }
}