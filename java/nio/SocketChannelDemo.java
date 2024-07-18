package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author goodtime
 * @create 2024-01-24 23:19
 */
public class SocketChannelDemo {
    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));

        //设置非阻塞
        socketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(15);

        socketChannel.read(buffer);
        socketChannel.close();
        System.out.println("read over");
    }
}
