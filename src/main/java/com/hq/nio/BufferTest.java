package com.hq.nio;

import java.nio.ByteBuffer;

/**
 * @program: him
 * @description:
 * @create: 2019-08-16 09:46
 **/
public class BufferTest {

    public static void main(String[] args) {
        //ByteBuffer可以存储不同的类型，但是取得时候需要按顺序取（字节码会自动识别）
        //如果循环取不同的类型，取出的值不是我们想要的值
        //分配内存来存储
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        byteBuffer.putInt(123);
        byteBuffer.putLong(55555L);
        byteBuffer.putChar('神');
        byteBuffer.putDouble(7.07d);
        byteBuffer.putShort((short)8);
        byteBuffer.putFloat(0.09f);

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getFloat());

        byteBuffer.flip();

        while(byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
    }
}
