package com.hq.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;

/**
 * @program: him
 * @description:
 * @create: 2019-08-16 09:52
 **/
public class BufferTest1 {
    /**
     * byteBuffer.slice()，相当于做一个buffer的快照
     *  1.slice前,设置position的limit，那么复制一个快照
     *  2.在这个快照上修改值，会实际改变buffer的值
     *  3.但是slicebuffer的位置不会影响buffer
     *
     */
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i=0;i<buffer.capacity();i++){
            buffer.put((byte)i);
        }

        //设置position的limit
        buffer.position(2);
        buffer.limit(6);
        while(buffer.hasRemaining()){
            System.out.println(buffer.get()); // 2 3 4 5
        }
        //get 后 position为6，limit为6
       //重新定义position的位置
        buffer.position(3);
        //slice会copy原buffer，position到limit之间的值，与buffer的值互相影响
        ByteBuffer sliceBuffer = buffer.slice();
        //  position = 0;
        //  limit = capacity;
        //  mark = -1;
        buffer.clear();
        for (int i=0; i<sliceBuffer.capacity();i++){
            sliceBuffer.put(i, (byte)(sliceBuffer.get(i)*2));
        }
        while (buffer.hasRemaining()){ // return position < limit;
            System.out.println(buffer.get());
        }
    }
}
