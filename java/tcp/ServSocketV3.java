package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 服务器端，改进版三(只负责收消息)
 */
public class ServSocketV3 {
    public static void main(String[] args) throws IOException {
        // 创建 Socket 服务器端
        ServerSocket serverSocket = new ServerSocket(9092);
        // 获取客户端连接
        Socket clientSocket = serverSocket.accept();
        // 使用线程池处理更多的客户端
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(100, 150, 100,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000));
        threadPool.submit(() -> {
            // 消息处理
            processMessage(clientSocket);
        });
    }
    /**
     * 消息处理
     * @param clientSocket
     */
    private static void processMessage(Socket clientSocket) {
        // 获取客户端发送的消息流对象
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()))) {
            while (true) {
                // 按行读取客户端发送的消息
                String msg = bufferedReader.readLine();
                if (msg != null) {
                    // 成功接收到客户端的消息并打印
                    System.out.println("接收到客户端的信息:" + msg);
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
