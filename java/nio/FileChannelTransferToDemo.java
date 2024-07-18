package nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author goodtime
 * @create 2024-01-24 23:03
 */
public class FileChannelTransferToDemo  {

    public static void main(String[] args) throws Exception {
        //创建2个fileChannel
        RandomAccessFile file  = new RandomAccessFile("/Users/goodtime/desktop/01.txt","rw");
        FileChannel channel = file.getChannel();

        RandomAccessFile file2  = new RandomAccessFile("/Users/goodtime/desktop/02.txt","rw");
        FileChannel channel2 = file2.getChannel();

        //from channel to channel2
        long position = 0;
        long size = channel.size();
        channel.transferTo(position,size,channel2);

        file.close();
        file2.close();
        System.out.println("结束拷贝");

    }



}
