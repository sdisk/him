package com.hq.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * Created by huang on 29/7/2019.
 */
public class TestServerInit extends ChannelInitializer<SocketChannel>
{

    @Override
    protected void initChannel(SocketChannel sc) throws Exception
    {
        //pipeLine管道，类似filter
        ChannelPipeline pipeline = sc.pipeline();
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("testHttpServerHanler", new TestHttpServerHanler());
    }
}
