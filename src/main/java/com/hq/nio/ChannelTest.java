package com.hq.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: him
 * @description:
 * @create: 2019-08-16 10:17
 **/
public class ChannelTest {
    /**
     * channel管道是双向的
     */
    public static void main(String[] args) throws Exception {
        FileInputStream is = new FileInputStream("src/main/resources/a.txt");
        FileChannel fileChannel = is.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        //从通道中读到buffer中
        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        byte [] bytes = new byte[256];
        int i = 0;
        while (byteBuffer.remaining() > 0){ // remaining  limit - position
            bytes[i] = byteBuffer.get();
            i++;
        }
        System.out.println(new String(bytes));
        System.out.println("###########");

        is = new FileInputStream("src/main/resources/b.txt");
        fileChannel = is.getChannel();
        byteBuffer.flip();
        fileChannel.read(byteBuffer);
        byteBuffer.flip();

        bytes = new byte[256];
        i =0;
        while(byteBuffer.hasRemaining()){
            bytes[i] = byteBuffer.get();
            i++;
        }
        System.out.println(new String(bytes));
        is.close();
    }
}
