package com.hq.nettyThi;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @program: him
 * @description:
 * @author: Mr.Huang
 * @create: 2019-08-13 15:31
 **/
public class ChatInitizlizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // DelimiterBasedFrameDecoder使用任何由用户提供的分隔符来提取帧的通用解码器
        //LineBasedFrameDecoder 提取由行尾符（\n 或者\r\n）分隔的帧的解码器。这个解码
        //器比 DelimiterBasedFrameDecoder 更快
        pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        //虽然默认StringEncoder默认就是UTF_8，但是最好还是写上
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));

        /**
         * ChannelPipeline pipeline = ch.pipeline();
         * pipeline.addLast(new LineBasedFrameDecoder(64 * 1024));
         */
        pipeline.addLast(new ChatServerHandler());
    }
}
