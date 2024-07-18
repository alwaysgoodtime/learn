package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 服务器端（只负责接收消息）
 */
public class ServerSocket {
    // 字节数组的长度
    private static final int BYTE_LENGTH = 20;

    public static void main(String[] args) throws IOException {
        // 创建 Socket 服务器
        java.net.ServerSocket serverSocket = new java.net.ServerSocket(9999);
        // 获取客户端连接
        Socket clientSocket = serverSocket.accept();
        // 得到客户端发送的流对象
        try (InputStream inputStream = clientSocket.getInputStream()) {
            while (true) {
                // 循环获取客户端发送的信息
                byte[] bytes = new byte[BYTE_LENGTH];
                // 读取客户端发送的信息
                int count = inputStream.read(bytes, 0, BYTE_LENGTH);
                if (count > 0) {
                    // 成功接收到有效消息并打印
                    System.out.println("接收到客户端的信息是:" + new String(bytes));
                }
            }
        }
    }
}