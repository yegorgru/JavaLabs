import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    void testHuman() {
        Human human = new Human(50, "Petro");
        assertEquals(50, human.getAge());
        assertEquals("Petro", human.getName());
    }
}