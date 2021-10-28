package com.jimmy.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xiongyang
 * @date 2020/8/6 15:18
 * @Description:  Netty服务端
 */
@Slf4j
@Component
public class NettyService {

    @Value("${netty.port}")
    private int port;

    public void start(){

        //配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            /**
             * ServerBootstrap 是一个启动NIO服务的辅助启动类
             */
            ServerBootstrap serverBootstrap = new ServerBootstrap();
                /**
                 * 设置group，将bossGroup， workerGroup线程组传递到ServerBootstrap
                 */
            serverBootstrap.group(bossGroup, workerGroup)
                /**
                 * ServerSocketChannel是以NIO的selector为基础进行实现的，用来接收新的连接，
                 * 这里告诉Channel通过NioServerSocketChannel获取新的连接
                 */
            .channel(NioServerSocketChannel.class)

                /**
                 * option是设置 bossGroup，childOption是设置workerGroup
                 * netty 默认数据包传输大小为1024字节,
                 * 设置它可以自动调整下一次缓冲区建立时分配的空间大小，
                 * 避免内存的浪费    最小  初始化  最大 (根据生产环境实际情况来定)
                 * 使用对象池，重用缓冲区
                 */
            .option(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(64, 10496, 1048576))

                /**
                 * 设置 I/O处理类,主要用于网络I/O事件，记录日志，编码、解码消息
                 */
            .childHandler(new MyNettyChannelInitializer())
            .childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(64, 10496, 1048576));
            /**
             * 绑定端口，同步等待成功
             */
            ChannelFuture f = serverBootstrap.bind(port).sync();
            /**
             * 等待服务器监听端口关闭 （等待服务端链路关闭之后main函数才退出）
             */
            f.channel().closeFuture().sync();
            log.info("======NettyService启动成功!!!=========");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }



}
