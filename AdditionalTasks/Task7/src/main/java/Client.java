import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Client {
    private static final Logger log = Logger.getLogger(Client.class.getName());
    private SocketChannel socketChannel;
    private ByteBuffer buffer;

    public Client(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.buffer = ByteBuffer.allocate(1000);
    }

    public static void main(String[] args) {
        try {
            Client client = new Client(SocketChannel.open(new InetSocketAddress("localhost", 4545)));
            client.run();
        } catch (IOException ex) {
            log.severe(ex.getMessage());
        }
    }

    public void run() throws IOException {
        send();
        getResponse();
    }

    public void send() throws IOException {
        Human human = new Human(19, "Yehor");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(human);
        objectOutputStream.flush();
        buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        socketChannel.write(buffer);
        log.info("Send human. Name: " + human.getName() + ". Age: " + human.getAge());
    }

    public void getResponse() throws IOException {
        ByteBuffer inputBuffer = ByteBuffer.allocate(256);
        socketChannel.read(inputBuffer);
        String response = new String(inputBuffer.array()).trim();
        log.info(response);
        buffer.clear();
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }
}
