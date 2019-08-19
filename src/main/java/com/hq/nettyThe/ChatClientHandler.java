package com.hq.nettyThe;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: him
 * @description:
 * @author: Mr.Huang
 * @create: 2019-08-13 16:18
 **/
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("form server："+msg);
        System.out.println(" over ");
    }
}
