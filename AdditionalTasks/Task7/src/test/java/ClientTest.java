import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.channels.SocketChannel;

import static org.junit.jupiter.api.Assertions.fail;

public class ClientTest {
    private SocketChannel clientSocket = Mockito.mock(SocketChannel.class);

    @Test
    public void testSendObject() {
        Client client = new Client(clientSocket);
        try {
            client.send();
            client.getBuffer().rewind();
            ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(client.getBuffer().array()));
            Human human = (Human) stream.readObject();
            assertEquals("Yehor", human.getName());
            assertEquals(19, human.getAge());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}