package com.hq.nettyTwo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: him
 * @description: netty既可以做客户端也可以作为服务端
 * @create: 2019-08-13 10:41
 **/
public class MyServer {
    public static void main(String[] args) {
        //定义eventLoop 事件循环
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //真正处理
        EventLoopGroup workGroup = new NioEventLoopGroup();
        //引导
        try {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class) //指定所使用的 NIO传输 Channel
                .childHandler(new MyServerInitizlizer()); //针对workGroup的handler

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync(); //绑定端口阻塞线程
            //对关闭的监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅关闭事件循环组
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
