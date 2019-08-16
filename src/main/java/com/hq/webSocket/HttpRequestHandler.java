package com.hq.webSocket;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @program: him
 * @description:
 * @create: 2019-08-14 10:00
 **/
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String wsUri;

    private static final File INDEX;

    static {
        URL location = HttpRequestHandler.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println("location: "+location);
        try{
            String path = location.toURI() + "templates/index.html";
            //String path = location.toURI() + "templates/test.txt";
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e){
            throw new IllegalStateException(
                    "Unable to locate index.html", e);
        }
    }
    public HttpRequestHandler(String wsUri){
        this.wsUri = wsUri;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        //如果请求了 WebSocket协议升级，则增加引用计数（调用 retain()方法）
        //并将它传递给下一个ChannelInboundHandler
        if (wsUri.equalsIgnoreCase(msg.getUri())){
            ctx.fireChannelRead(msg.retain());
        } else {
            if (HttpHeaders.is100ContinueExpected(msg)){
                send100Continue(ctx);
            }
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            HttpResponse response = new DefaultHttpResponse(msg.getProtocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain;charset=UTF-8");
            boolean keepAlive = HttpHeaders.isKeepAlive(msg);
            System.out.println("keepAlive:" + keepAlive);
            if (keepAlive){
                //添加keepAlive所需要的HTTP头消息
                response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, file.length());
                response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            }
            ctx.write(response);
            //将 index.html写到客户端
            if (ctx.pipeline().get(SslHandler.class) == null){
                //如果不需要加密和压缩,可以使用DefaultFileRegion 来达到最佳效率。这将会利用零拷贝特性来进行内容的传输
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                //ChunkedNioFile 和 ChunkedFile 类似，只是它使用了 FileChannel
                //ChunkedFile 从文件中逐块获取数据，当你的平台不支持零拷贝或者你需要转换数据时使用
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            //写LastHttpContent并冲刷到客户端，HttpRequestHandler 将写一个 LastHttpContent 来标记响应的结束
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if (!keepAlive){
                //如果没有请求keepAlive，则在写操作完成后关闭channel
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }
    private static void send100Continue(ChannelHandlerContext ctx){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
