package com.hq.netty;

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
        //bossGroup主线程处理，死循环，一直接受客户端连接， 事件循环组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //辅线程处理
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInit());

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
