package com.hq.nettyFiv;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @program: him
 * @description:
 * @author: Mr.Huang
 * @create: 2019-08-13 17:56
 **/

/**
 * 这里的泛型是TextWebSocketFrame表示一个文本帧（Frame）
 *
 * BinaryWebSocketFrame 包含了二进制数据
 * TextWebSocketFrame 包含了文本数据
 * ContinuationWebSocketFrame 包含属于上一个BinaryWebSocketFrame或TextWebSocketFrame 的文本数据或者二进制数据
 * CloseWebSocketFrame 表示一个 CLOSE 请求，包含一个关闭的状态码和关闭的原因
 * PingWebSocketFrame 请求传输一个 PongWebSocketFrame
 * PongWebSocketFrame 作为一个对于 PingWebSocketFrame 的响应被发送
 */
public class TextWebSocketFranmeHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务端收到的消息："+msg.text()); //使用text方法才可以得到正确的消息

        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器的时间："+LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        /**
         *  id表示唯一，有长有短，长的asLongText，唯一。短的asShortText()不唯一
         */
        System.out.println("handlerAdded的ID:"+ ctx.channel().id().asShortText());
    }

    /**
     * 一个很有趣的现象，如果客户端刷新一下，实际上会调用这个方法，因为连接断了，新建了一个连接，就是先handlerRemoved，然后又handlerAdded
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved的ID："+ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常，关闭连接");
        ctx.close();
    }
}
