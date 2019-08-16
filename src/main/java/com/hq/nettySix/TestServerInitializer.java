package com.hq.nettySix;

import com.hq.nettyOne.TestHttpServerHanler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @program: him
 * @description: Protobuf 载体，编码&解码
 * @author: Mr.Huang
 * @create: 2019-08-15 14:04
 *
 * ProtobufDecoder 使用 protobuf 对消息进行解码
 * ProtobufEncoder 使用 protobuf 对消息进行编码
 * ProtobufVarint32FrameDecoder 根据消息中的 Google Protocol Buffers 的“Base 128 Varints”a
 * 整型长度字段值动态地分割所接收到的 ByteBuf
 * ProtobufVarint32LengthFieldPrepender 向 ByteBuf 前追加一个 Google Protocal Buffers 的“Base
 * 128 Varints”整型的长度字段值
 *
 **/
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //pipeline.addLast(new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new TestHttpServerHanler());
    }
}
