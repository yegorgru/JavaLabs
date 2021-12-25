import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

class ServerTest {
    @Test
    public void testReadingObject() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(byteArrayOutputStream);
        Human human = new Human(5, "Boy");
        stream.writeObject(human);
        stream.close();
        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        Server server = new Server();
        Human received = server.readHuman(buffer);
        assertEquals(received.getAge(), human.getAge());
        assertEquals(received.getName(), human.getName());
    }
}