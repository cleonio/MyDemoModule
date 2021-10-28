package com.jimmy.nettyServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xiongyang
 * @date 2020/8/6 10:08
 * @Description:
 *
 * 一个最简单的Netty服务端包含了五个步骤：
 *
 * （1）构建一对主从线程组
 * （2）定义服务器启动类
 * （3）为服务器设置Channel
 * （4）设置处理从线程池的助手类初始化器
 * （5）监听启动和关闭的服务器
 *
 */
public class HelloNettyServer {

    public static void main(String[] args) {

        /**
         * 定义对主从线程组
         */
        //主线程组，用于接收客户端的链接，但不做任何处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //定义从线程组，主线程组会把任务转给从线程组进行处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        /**
         * 服务启动类
         */
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //需要去针对一个之前的线程模型（上面定义的是主从线程）
            serverBootstrap.group(bossGroup,workerGroup)
                    //设置NIO的双向通道
                    .channel(NioServerSocketChannel.class)
                    /**
                     * 设置channel初始化
                     */
                    .childHandler(new HelloNettyServerInitializer());

             //绑定端口，并设置同步方式，是一个异步channel
            ChannelFuture future = serverBootstrap.bind(8888).sync();

            //获取某个客户端对应的channel，关闭并设置同步方式
            future.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //使用一种优雅的方式进行关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
