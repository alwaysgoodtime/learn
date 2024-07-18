package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author goodtime
 * @create 2024-01-24 22:24
 */
public class FileChannelReadDemo {
    //通过channel读取数据到buffer中
    public static void main(String[] args) throws Exception {
        //创建一个fileChannel
        RandomAccessFile file  = new RandomAccessFile("/Users/goodtime/desktop/01.txt","rw");
        FileChannel channel = file.getChannel();

        //创建 buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //读取 fileChannel 数据到 buffer
        int byteRead = channel.read(buffer);
        while (byteRead != -1){//如果为-1，表示结束，表示有多少个字节读到了buffer中
            System.out.println("读取了：" + byteRead);
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.println((char)buffer.get());
            }
            buffer.clear();
            byteRead = channel.read(buffer);
        }

        file.close();
        System.out.println("读取完毕");
    }
}
