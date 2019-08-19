package com.hq.nettyTwo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @program: him
 * @description:
 * @create: 2019-08-13 10:52
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     *
     * @param ctx : 请求上下文消息，可以获得channel，远程地址等
     * @param msg : 客户端发送的消息
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端收到:"+ ctx.channel().remoteAddress()+" : " +msg);
        ctx.channel().writeAndFlush("发给客户端："+ UUID.randomUUID());
    }

    /**
     * 出现异常，关闭连接
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelInactive");
        super.channelInactive(ctx);
    }

    /**
     * 通知ChannelInboundHandler最后一次对channelRead()的调用是当前批量读取中的最后一条消息；
     * @param ctx
     * @throws Exception
     */
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("server channelReadComplete");
//        //将未决消息冲刷到远程节点，并且关闭channel
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
//    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("server userEventTriggered");
        super.userEventTriggered(ctx, evt);
    }
}
