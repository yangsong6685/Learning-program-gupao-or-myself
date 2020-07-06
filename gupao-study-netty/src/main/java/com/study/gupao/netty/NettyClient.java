package com.study.gupao.netty;

import com.study.gupao.io.AbstractIOClient;
import com.study.gupao.netty.core.ClientSocketChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;

public class NettyClient  extends AbstractIOClient {

    private Bootstrap bootstrap;
    private NioEventLoopGroup nioEventLoop;
    private int port;

    public NettyClient(int port){
        this.port = port;
    }
    @Override
    public void start() throws IOException {
        bootstrap = new Bootstrap();
        nioEventLoop = new NioEventLoopGroup();
        bootstrap.group(nioEventLoop)
                .channel(NioSocketChannel.class)
                .handler(new ClientSocketChannelInitializer());
        try {
            ChannelFuture channelFuture = bootstrap.connect("192.168.8.102",port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            nioEventLoop.shutdownGracefully();
        }
    }
    @Override
    public boolean close() {
        return false;
    }

}
