package nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author goodtime
 * @create 2024-01-24 23:10
 */
public class ServerSocketChannelDemo {
    public static void main(String[] args) throws Exception {
        //端口号
        int port = 8888;

        //buffer
        ByteBuffer buffer = ByteBuffer.wrap("hello goodtime".getBytes());

        //ServerSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //绑定端口
        ssc.socket().bind(new InetSocketAddress(port));

        //设置非阻塞,会决定ssc.accept()是否会一直阻塞
        ssc.configureBlocking(false);

        //监听是否有新链接传入
        while (true){
            System.out.println("等待连接传入");
            SocketChannel accept = ssc.accept();
            if(accept == null){
                System.out.println("没有新连接传入");
                Thread.sleep(2000);
            }else {
                System.out.println("Incoming connection from :" + accept.socket().getRemoteSocketAddress());
                buffer.rewind();//让其指向第一个位置
                accept.write(buffer);
                accept.close();
            }
        }


    }
}
