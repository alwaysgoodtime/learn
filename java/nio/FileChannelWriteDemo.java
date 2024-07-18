package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author goodtime
 * @create 2024-01-24 22:40
 */
public class FileChannelWriteDemo {
    public static void main(String[] args) throws Exception{
        //打开一个fileChannel
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/goodtime/desktop/01.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();

        //创建buffer对象
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        String newData = "1232456";
        buffer.clear();

        //写入内容
        buffer.put(newData.getBytes());

        buffer.flip();

        //fileChannel完成最终实现
        while (buffer.hasRemaining()){
            channel.write(buffer);
        }

        //关闭close
        channel.close();


    }
}
