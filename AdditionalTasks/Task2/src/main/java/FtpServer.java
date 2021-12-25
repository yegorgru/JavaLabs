import java.util.logging.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class FtpServer {
    private static final Logger log = Logger.getLogger(FtpServer.class.getName());

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private ByteBuffer buff = ByteBuffer.allocate(100);

    public FtpServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(21));
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        log.info("Server started");
    }

    public static void main(String args[]) throws IOException {
        FtpServer ftpServer = new FtpServer();
        ftpServer.listen();
    }

    public void listen() throws IOException {
        while (!Thread.interrupted()) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                handleKey(key);
            }
        }
    }

    public void handleKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            log.info("Accept new connection");
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            SelectionKey newKey = socketChannel.register(selector, SelectionKey.OP_WRITE);
            String response = "220 \r\n";
            newKey.attach(response);
        }
        else if (key.isReadable()) {
            read(key);
        }
        else if (key.isWritable()) {
            write(key);
        }
    }

    public void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buff.clear();
        int count = socketChannel.read(buff);
        String command = new String(buff.array(), 0, count);
        buff.clear();
        if (command != null) {
            log.info("Command is：" + command);
            String response = "hi";
            key.interestOps(SelectionKey.OP_WRITE);
            key.attach(response);
        }
    }

    public void write(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        String response = ((String) key.attachment());
        ByteBuffer block = ByteBuffer.wrap(response.getBytes());
        block.clear();
        block.put(response.getBytes());
        block.flip();
        int i = socketChannel.write(block);
        log.info("Sent: " + i + " bytes of task2.data");
        log.info("The server sends task2.data to the client--：" + response);
        key.interestOps(SelectionKey.OP_READ);
    }
}
