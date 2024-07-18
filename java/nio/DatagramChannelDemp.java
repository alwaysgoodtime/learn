package nio;

import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author goodtime
 * @create 2024-01-24 23:25
 */
public class DatagramChannelDemp {

    //发送实现
    @Test
    public void  sendDatagram() throws Exception {

        DatagramChannel sendChannel = DatagramChannel.open();
        InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1",9999);

        //发送
        while (true){

            ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8));
            sendChannel.send(buffer,sendAddress);
            System.out.println("发送完成");
            Thread.sleep(1000);
        }

    }

    @Test
    public void receiveDatagram() throws Exception {
        //打开DatagramChannel
        DatagramChannel receiveChannel =  DatagramChannel.open();
        InetSocketAddress receiveAddress = new InetSocketAddress("127.0.0.1",9999);
        receiveChannel.bind(receiveAddress);

        ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);


        while (true){
            //清空
            receiveBuffer.clear();

            SocketAddress receive = receiveChannel.receive(receiveBuffer);

            receiveBuffer.flip();

            System.out.println(receive.toString());

            System.out.println(Charset.forName("UTF-8").decode(receiveBuffer));

        }
    }

    //datagram read 与 write
    @Test
    public void  testConnect() throws Exception {
        //打开DatagramChannel
        DatagramChannel sendChannel =  DatagramChannel.open();
        InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1",9999);
        sendChannel.bind(sendAddress);

        //连接
        sendChannel.connect(sendAddress);

        sendChannel.write(ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8)));

        //buffer
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        while (true){
            readBuffer.clear();
            sendChannel.read(readBuffer);
            readBuffer.flip();
            System.out.println(Charset.forName("UTF-8").decode(readBuffer));
        }


    }
}
