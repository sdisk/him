package com.hq.nettyThi;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: him
 * @description:
 * @create: 2019-08-13 15:38
 **/
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 定义一个channelGroup，用于保存多个用户，
     * 一个用户表示一个channel，将他们加入到一个组
     * set集合
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach( ch ->{
            if (channel != ch){
                ch.writeAndFlush("[客户]"+channel.remoteAddress()+"发送了消息--"+msg+"\n");
            } else {
                ch.writeAndFlush("[自己]发送了消息--"+msg+"\n");
            }
        });
    }

    /**
     * 连接建立好后立即执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        /**
         * channelGroup的writeAndFlush有点特别，他将循环对里面每一个channel进行输出
         * 如：假如A上线，会通知channelGroup其他channel，但是不会通知A，因为此时没有加入A
         * 如果也想通知自己，那么在输出前将自己加入channelGroup就好（注意他们的顺序）
         */
        channelGroup.writeAndFlush("[客户端]-"+channel.remoteAddress()+"加入\n");
        channelGroup.add(channel);
    }

    /**
     * 断开后立即执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        /**
         * 如果是离开，相对应的应该移除channel，但是这里不需要
         * 因为neety，自动将它移除了
         */
        channelGroup.writeAndFlush("[客户端]-"+channel.remoteAddress()+"离开了\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("haha,上线了");
        System.out.println(channel.remoteAddress()+"上线啦！！！");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"离线了！！！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("发生异常，关闭连接");
        ctx.close();
    }
}
