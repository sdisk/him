package com.hq.nettyFou;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @program: him
 * @description:
 * @create: 2019-08-13 17:28
 **/
public class MyServerInitizlizer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /**
         * IdleStateHandler 状态监测handler readerIdleTime,writeIdleTime,allIdleTime
         *
         */
        pipeline.addLast(new IdleStateHandler(5,3, 2, TimeUnit.SECONDS));
        pipeline.addLast(new MyServerHandler());
    }
}
