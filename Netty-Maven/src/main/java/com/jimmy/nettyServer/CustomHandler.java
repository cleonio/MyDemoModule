package com.jimmy.nettyServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author xiongyang
 * @date 2020/8/6 10:48
 * @Description: 创建自定义助手类
 */
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel注册");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel未注册");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel活跃状态");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端断开连接之后");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel读取数据完毕");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("用户事件触发");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel可写事件更改");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("捕获channel异常");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("助手类添加");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("助手类移除");
        super.handlerRemoved(ctx);
    }

    /**
     *
     * ChannelHandlerContext：上下文对象
     * @param channelHandlerContext
     * @param httpObject
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

        //获取当前channel
        Channel currentChannel = channelHandlerContext.channel();

        //判断httpObject是否为一个HttpRequest的请求类型
        if (httpObject instanceof HttpRequest){

            System.out.println(currentChannel.remoteAddress());

            //定义发送的消息（不是直接发送，而是要把数据拷贝到缓冲区，通过缓冲区）
            //Unpooed：是一个专门用于拷贝Buffer的深拷贝，可以有一个或多个
            //CharsetUtil.UTF_8：Netty提供
            ByteBuf content = Unpooled.copiedBuffer("Hello Netty", CharsetUtil.UTF_8);

            //构建一个HttpResponse，响应客户端
            FullHttpResponse response =
                    /**
                     * params1:针对Http的版本号
                     * params2:状态（响应成功或失败）
                     * params3:内容
                     */
                    //HttpVersion.HTTP_1_1：默认开启keep-alive
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            //设置当前内容长度、类型等
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            //readableBytes：可读长度
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            //通过长下文对象，把响应刷到客户端
            channelHandlerContext.writeAndFlush(response);

        }


    }
}
