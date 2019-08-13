package com.hq.nettyOne;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by huang on 29/7/2019.
 */
public class TestServer
{
    public static void main(String[] args) throws InterruptedException
    {
        //bossGroup主线程处理，死循环，一直接受客户端连接， 事件循环组 bossGroup是一直监控连接，分配给workGroup
        //创建并分配一个NioEventLoopGroup实例以进行事件的处理，异步处理的事件循环对象
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //辅线程处理 workGroup 是真正处理，工作的
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            //ServerBootstrap helpClass，简化netty服务器的创建工作，引导类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //方法链
            serverBootstrap.group(bossGroup, workGroup)
                    //指定所使用的NIO传输chanel
                    .channel(NioServerSocketChannel.class)
                    //handler() -> bossGroup, childHandler() -> workGroup
                    .childHandler(new TestServerInit());
            //ChannelFutureListener
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            //关闭监听
            channelFuture.channel().closeFuture().sync();
        } finally
        {
            //优雅关闭
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
