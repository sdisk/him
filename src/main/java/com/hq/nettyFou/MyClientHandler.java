package com.hq.nettyFou;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @program: him
 * @description:
 * @create: 2019-08-13 15:15
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("客户端输出："+ msg);
        ctx.writeAndFlush("发送到服务端："+ LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("开始啦");
        //ctx.writeAndFlush("来自客户端的消息："+"Hello " + LocalDateTime.now());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
