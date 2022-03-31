package com.hq.webSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;

/**
 * @program: him
 * @description:
 * @author: Mr.Huang
 * @create: 2019-08-14 10:56
 **/
public class ChatServer {
    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    private final EventLoopGroup group = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(InetSocketAddress address){
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group).channel(NioServerSocketChannel.class)
                .childHandler(createInitializer(channelGroup));
        ChannelFuture channelFuture = bootstrap.bind(address);
        channelFuture.syncUninterruptibly();
        channel = channelFuture.channel();
        return channelFuture;
    }
    protected ChannelInitializer<Channel> createInitializer(ChannelGroup group){
        return new ChatServerInitializer(group);
    }

    public void destroy(){
        if (null != channel){
            channel.close();
        }
        channelGroup.close();
        group.shutdownGracefully();
    }

    public static void main(String[] args) {
//        if (args.length != 1){
//            System.out.println("Please give port");
//            System.exit(1);
//        }
//        int port = Integer.parseInt(args[0]);
        int port = 8899;

        //ssl
//        SelfSignedCertificate certificate = new SelfSignedCertificate();
//        SslContext context = SslContext.newServerContext(certificate.certificate, certificate.privateKey());
//        final ChatServer endPoint = new new ChatServer(context);

        final  ChatServer endPoint = new ChatServer();
        ChannelFuture future = endPoint.start(new InetSocketAddress(port));
//        Runtime.getRuntime().addShutdownHook(new Thread(){
//            @Override
//            public void run (){
//                endPoint.destroy();
//            }
//        });
        Runtime.getRuntime().addShutdownHook(new Thread(() ->  {
            {
                endPoint.destroy();
            }
        }));
        //closeFuture()是开启了一个channel的监听器，负责监听channel是否关闭的状态，如果未来监听到channel关闭了，子线程才会释放，
        // syncUninterruptibly()让主线程同步等待子线程结果
        future.channel().closeFuture().syncUninterruptibly();
    }

}
