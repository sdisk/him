package com.hq.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: him
 * @description:
 * @create: 2019-08-16 10:40
 **/
public class MappedByteBufferTest {
    // MappedByteBuffer可以让文件直接在内存中进行修改，而不需要操作系统拷贝一次
    //DirectByteBuffer也是MappedByteBuffer的子类
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/c.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        //0到6的范围内直接在内存修改
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 6);
//        mappedByteBuffer.put(0, (byte) 'b');
//        mappedByteBuffer.put(1, (byte) 'b');
        mappedByteBuffer.put(2, (byte) 'b');
        mappedByteBuffer.put(3, (byte) 'b');
        randomAccessFile.close();
        //注意：执行完代码后，IDEA的文件并没有及时改变，但是如果我们在外面打开文件，他的确是发生改变的。
    }
}
