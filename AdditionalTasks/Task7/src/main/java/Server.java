import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class Server {
    private static final Logger log = Logger.getLogger(Server.class.getName());
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.setUp();
            server.run();
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
    }

    public void run() throws IOException {
        while (true){
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    acceptConnection();
                }
                if(selectionKey.isReadable()){
                    receiveHuman(selectionKey);
                }
                iterator.remove();
            }
        }
    }

    public void setUp() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 4545));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        log.info("Server started, port 5000");
    }

    public void acceptConnection() throws IOException {
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    public void receiveHuman(SelectionKey selectionKey) throws IOException {
        SocketChannel client = (SocketChannel) selectionKey.channel();
        try{
            ByteBuffer buffer = ByteBuffer.allocate(1000);
            client.read(buffer);
            Human human = readHuman(buffer);
            log.info("Receive human. Name: " + human.getName() + ". Age: " + human.getAge());
            client.write(ByteBuffer.wrap("Success:)".getBytes()));
        } catch (Exception ex) {
            log.severe(ex.getMessage());
            client.write(ByteBuffer.wrap("Something went wrong:(".getBytes()));
        }
        client.close();
    }

    public Human readHuman(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        buffer.rewind();
        ObjectInputStream reader = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
        return (Human) reader.readObject();
    }
}