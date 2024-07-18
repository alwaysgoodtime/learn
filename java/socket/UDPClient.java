package socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author goodtime
 * @create 2024-01-24 13:42
 */
public class UDPClient {
    public static void main(String[] args) {

        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            ds.setSoTimeout(1000);
            ds.connect(InetAddress.getByName("localhost"), 6666); // 连接指定服务器和端口
            // 发送:
            byte[] data = "Hello".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length);
            ds.send(packet);
            // 接收:
            byte[] buffer = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
            System.out.println(resp);
            ds.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭:
            ds.close();
        }

    }
}
