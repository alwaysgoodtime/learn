package nio.zerocopy;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Client的目标是，与server建立连接，并接收Server发来的文件
 *
 * @author goodtime
 * @create 2024-01-26 14:46
 */
public class Client {

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("/users/goodtime/desktop/2.txt", "rw");
        FileChannel fileChannel = raf.getChannel();
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));

        //设置非阻塞
        socketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(100);
        socketChannel.read(buffer);
        System.out.println("read over");
        while (buffer.hasRemaining()) {
            char c = buffer.getChar();
            System.out.println(Integer.valueOf(c));

        }

        buffer.flip();

        //写入文件
        fileChannel.write(buffer);

        socketChannel.close();
        raf.close();
    }
}
