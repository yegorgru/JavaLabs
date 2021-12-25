import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ThreadInfoTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testThreadServiceMethods(){
        ThreadGroup threadGroup1 = new ThreadGroup("FIRST GROUP");
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1, "SECOND GROUP");
        ThreadGroup threadGroup3 = new ThreadGroup(threadGroup2, "THIRD GROUP");

        new Thread(threadGroup1, () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "firstThread").start();


        new Thread(threadGroup2, () -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "secondThread").start();

        new Thread(threadGroup2, () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thirdThread").start();

        new Thread(threadGroup3, () -> {
            try {
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "fourthThread").start();

        ThreadInfo threadService = new ThreadInfo();
        threadService.print(threadGroup1);
        long endTime = System.currentTimeMillis() + 15000;
        while (System.currentTimeMillis() < endTime);
        assertEquals("FIRST GROUP\r\n" +
                "Threads at FIRST GROUP:\r\n" +
                "  firstThread\r\n" +
                "Thread groups in FIRST GROUP:\r\n" +
                "  SECOND GROUP\r\n" +
                "  Threads at SECOND GROUP:\r\n" +
                "    secondThread\r\n" +
                "    thirdThread\r\n" +
                "  Thread groups in SECOND GROUP:\r\n" +
                "    THIRD GROUP\r\n" +
                "    Threads at THIRD GROUP:\r\n" +
                "      fourthThread\r\n" +
                "FIRST GROUP\r\n" +
                "Thread groups in FIRST GROUP:\r\n" +
                "  SECOND GROUP\r\n" +
                "  Thread groups in SECOND GROUP:\r\n" +
                "    THIRD GROUP\r\n" +
                "    Threads at THIRD GROUP:\r\n" +
                "      fourthThread\r\n" +
                "FIRST GROUP\r\n" +
                "Thread groups in FIRST GROUP:\r\n" +
                "  SECOND GROUP\r\n" +
                "  Thread groups in SECOND GROUP:\r\n" +
                "    THIRD GROUP\r\n" +
                "    Threads at THIRD GROUP:\r\n" +
                "      fourthThread\r\n", outContent.toString());
    }
}