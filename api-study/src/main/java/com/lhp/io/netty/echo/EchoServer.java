package com.lhp.io.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Data;

import java.net.InetSocketAddress;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/7/4 15:42
 */
@Data
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }
    public static void main(String[] args) throws Exception {
        new EchoServer(18888).start();

    }
    public void start() throws Exception {
        //消息处理逻辑
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)//指定所使用的 NIO 传输 Channel
                    .localAddress(new InetSocketAddress(port))//使用指定的 端口设置套 接字地址
                    // 添加一个 EchoServer- Handler 到子 Channel 的 ChannelPipeline
                    /**
                     * 你使用了一个特殊的类——ChannelInitializer。这是关键。当一个新的连接 被接受时，一个新的子 Channel 将会被创建，
                     * 而 ChannelInitializer 将会把一个你的 EchoServerHandler 的实例添加到该 Channel 的 ChannelPipeline 中。
                     * 正如我们之前所 解释的，这个 ChannelHandler 将会收到有关入站消息的通知。
                     */
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            // 异步地绑定服务器; 调用 sync()方法阻塞 等待直到绑定完成
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            //获取 Channel 的 CloseFuture，并 且阻塞当前线 程直到它完成
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}