package com.hq.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * Created by huang on 29/7/2019.
 */
public class TestHttpServerHanler extends SimpleChannelInboundHandler<HttpObject>
{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject
            httpObject) throws Exception
    {


        if (httpObject instanceof HttpRequest){
            //读取客户端请求，并返回响应
            System.out.println("执行了channelRead0");
            HttpRequest request = (HttpRequest) httpObject;
            System.out.println("请求的方法名："+request.getMethod().name());
            URI uri = new URI(request.uri());
            //favicon.ico
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求favicon.ico");
                return;
            }
            //定义ByteBuf传输内容
            ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            //定义header
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //返回结果
            channelHandlerContext.writeAndFlush(response);
        }
    }
}
