package com.jimmy.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author xiongyang
 * @date 2020/8/6 15:24
 * @Description:
 */
public class MyNettyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline channelPipeline = channel.pipeline();
        /**
         * WebSokect基于Http，所以要有相应的Http编解码器，HttpServerCodec()
         */
       /* channelPipeline.addLast(new HttpServerCodec());

        channelPipeline.addLast(new ChunkedWriteHandler());

        channelPipeline.addLast(new HttpObjectAggregator(2048*64));*/

        //================华丽的分割线：以上是用于支持Http协议================
        //================华丽的分割线：以下是用于支持WebSoket==================

        //channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        channelPipeline.addLast(new MyNettyChannelInboundHandler());
    }
}
