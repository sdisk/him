package com.hq.nettyFou;

import com.hq.nettyTwo.MyClientInitizlizer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program: him
 * @description:
 * @create: 2019-08-13 15:08
 **/
public class MyClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            /**
             * 1.客户端不在是ServerBootstrap而是Bootstrap
             * 2.反射类不是NioServerSocketChannel而是NioSocketChannel
             * 3.一般使用handler，而不是用childHandler
             * 4.不是bind绑定端口而是connect
             */
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientInitizlizer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }



    }
}
