package com.jimmy.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import java.nio.charset.StandardCharsets;

/**
 * @author xiongyang
 * @date 2020/8/13 10:24
 * @Description:
 */
@Slf4j
public class MyNettyClinetHandler extends ChannelHandlerAdapter {


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("MyNettyClinet exceptionCaught error");
        ctx.close();
    }

    /**
     * 当客户端和服务端TCP链路建立成功之后，Netty的NIO线程会调用channelActive方法
     * 调用ChannelHandlerContext的writeAndFlush方法将请求消息发送给服务端。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("NettyClient  channelActive... ");
        byte[] req = "this message from client".getBytes();
        ByteBuf messgae = Unpooled.copiedBuffer(req);
        messgae.writableBytes();
        ctx.writeAndFlush(messgae);
        System.out.println("NettyClinet 发送了消息");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8);
        System.out.println("MyNettyClinet : "+body);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("channelReadComplete");
    }


}
