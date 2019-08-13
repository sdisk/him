package com.hq.nettyFou;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @program: him
 * @description:
 * @create: 2019-08-13 17:31
 *
 * 注意这里继承的不是SimpleChannelInboundHandler，而是他的父类ChannelInboundHandlerAdapter
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            String sventType = null;
            switch (event.state()){
                case READER_IDLE:
                    sventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    sventType = "写空闲";
                    break;
                case ALL_IDLE:
                    sventType = "读写空闲";
                    break;
            }
            /**
             * 这个空闲指的是当前实现这个handler的类，是server
             * 如果这个server没有接受到客户端的数据，就是读空闲
             * 如果server没有发送消息，就是写空闲
             */
            System.out.println(ctx.channel().remoteAddress()+"--超时事件--"+sventType);
            //如果不关闭，会一直循环判断
            //每次触发了就关闭连接了，只会触发一个事件发生，测试时需要注意
            ctx.channel().close();
        }
    }
}
