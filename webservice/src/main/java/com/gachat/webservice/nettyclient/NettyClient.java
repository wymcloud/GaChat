package com.gachat.webservice.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Component
public class NettyClient {

    private final static Logger log = LoggerFactory.getLogger(NettyClient.class);

    private EventLoopGroup group = new NioEventLoopGroup();

    @Resource
    private NettyClientConfig nettyConfig;
    /**
     * 连接方法
     */
    @PostConstruct
    public void connect() throws InterruptedException {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new NettyClientInitializer());
            Channel channel = bootstrap.connect(nettyConfig.getHost(), nettyConfig.getPort()).sync().channel();
            // 发送json字符串
            String msg = "{\"name\":\"admin\",\"age\":27}\n";
            channel.writeAndFlush(msg);
            channel.closeFuture().sync();
    }

    @PreDestroy
    public void destory() throws InterruptedException {
        group.shutdownGracefully().sync();
        log.info("关闭Netty");
    }

}
