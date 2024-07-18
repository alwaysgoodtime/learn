package nio.zerocopy;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Server端的目标是，监听Client的连接请求，并把文件传到Client端口
 *
 * @author goodtime
 * @create 2024-01-26 14:45
 */
public class Server {

    public static void main(String[] args) throws Exception {

        RandomAccessFile raf = new RandomAccessFile("/users/goodtime/desktop/1.txt", "rw");
        FileChannel fileChannel = raf.getChannel();


        //ServerSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("127.0.0.1", 6666));
        ssc.configureBlocking(true);


        SocketChannel accept = ssc.accept();
        // 直接使用了transferTo()进行通道间的数据传输
        fileChannel.transferTo(0, fileChannel.size(), accept);
        System.out.println("send over");

        raf.close();
    }

}
