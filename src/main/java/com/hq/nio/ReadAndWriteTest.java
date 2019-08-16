package com.hq.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: him
 * @description:
 * @create: 2019-08-16 10:50
 **/
public class ReadAndWriteTest {

    /**
     * copy文件
     * 1.获取输入和输出的管道
     * 2.创建buffer
     * 3.写入buffer
     * 4.读出buffer
     */
    public static void main(String[] args) throws Exception {
        FileInputStream is = new FileInputStream("src/main/resources/a.txt");
        FileOutputStream os = new FileOutputStream("src/main/resources/d.txt");
        FileChannel inChannel = is.getChannel();
        FileChannel outChannel = os.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while(true){
            /*这里必须使用clear（）复原，如果不复原，positon和limit位置相同
            此时不会读取数据，因为可以认为buffer没有空间了，所以read=0,
            read都没有等于-1的机会，等于-1是因为输入流那边文件到头了返回-1,
            这种情况根本没有回去读，所以为0，然后之后flip把postition刷新回了0位置
            开始循环读出数据。*/
            byteBuffer.clear();
            int read = inChannel.read(byteBuffer);
            System.out.println("read:"+ read);
            if (read == -1){
                break;
            }
            byteBuffer.flip();
            outChannel.write(byteBuffer);
        }
        inChannel.close();
        outChannel.close();
        os.close();
        is.close();
        System.out.println("copy完毕");
    }
}
