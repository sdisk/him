package com.hq.nettyOne;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * Created by huang on 29/7/2019.
 */
public class TestServerInit extends ChannelInitializer<SocketChannel>
{
    /**
     * 初始化通道
     * 当一个新的连接被接受时，一个新的子 Channel 将会被创建，而 ChannelInitializer 将会把一个你的
     * ServerHandler 的实例添加到该 Channel 的 ChannelPipeline 中
     * @param sc
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel sc) throws Exception
    {
        //pipeLine管道，类似filter
        ChannelPipeline pipeline = sc.pipeline();
        //server端，httpServerCodec  HttpClientCodec
        //httpServerCodec将字节解码为 HttpRequest、HttpContent 和 LastHttpContent。并将 HttpRequest、HttpContent 和 LastHttpContent 编码为字节
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("testHttpServerHanler", new TestHttpServerHanler());
    }
}
