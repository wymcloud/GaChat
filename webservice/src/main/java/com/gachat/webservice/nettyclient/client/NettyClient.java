package com.gachat.webservice.nettyclient.client;

import com.gachat.webservice.nettyclient.handle.NettyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@EqualsAndHashCode
@Data
public class NettyClient implements INettyClient {
    /**
     * s
     * 各自客户端的channel
     */
    private Channel channel;

    private EventLoopGroup group = new NioEventLoopGroup();


    private String remoteAddress;
    private Integer remotePort;
    /**
     * 失败的请求
     */
    private transient Queue<Object> failMessages = new ConcurrentLinkedQueue<>();

    /**
     * 是否初始化过
     */
    private volatile boolean initialized = Boolean.FALSE;

    /**
     * 是否存活
     */
    private volatile boolean isLive = Boolean.FALSE;


    public NettyClient(String remoteAddress, Integer remotePort) {
        this.remoteAddress = remoteAddress;
        this.remotePort = remotePort;
    }

    /**
     * 测试与netty服务器连通
     */


/*
    public static void main(String[] args) throws InterruptedException {
        NettyClient nettyClient = new NettyClient();
        nettyClient.init();
        nettyClient.connect();
        nettyClient.send("测试");
        */
/*Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new NettyClientInitializer());
        Channel channel = bootstrap.connect("127.0.0.1",8082).sync().channel();
        // 发送json字符串
        String msg = "{\"name\":\"admin\",\"age\":27}\n";
        channel.writeAndFlush(msg);
        channel.closeFuture().sync();*//*


    }
*/
    public void testConnect(String messga) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new NettyClientInitializer());
        Channel channel = bootstrap.connect("127.0.0.1", 8082).sync().channel();
        channel.writeAndFlush(messga);
        channel.closeFuture().sync();
    }

   /* @PostConstruct
    public void testConnect() throws InterruptedException {
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
    }*/

    private void connect() throws InterruptedException {
        channel = configBootstrap().connect().sync().channel();
    }

    private Bootstrap configBootstrap() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.remoteAddress(remoteAddress, remotePort);
        bootstrap.group(group).channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new NettyClientInitializer());
        return bootstrap;
    }


    @Override
    public void init() {
        try {
            connect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            initialized = Boolean.TRUE;
        }

    }

    @Override
    public void destroy() {
        try {
            group.shutdownGracefully().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("关闭Netty");
    }


    @Override
    public void send(Object object) {
        init();
        if (!initialized) {
            log.warn("netty client not init, message: {}", toString());
            failMessages.add(object);
            return;
        }
        if (channel == null) {
            log.warn("channel  is empty! message: {}", toString());
            failMessages.add(object);
            return;
        }
        channel.writeAndFlush(object).addListener(channelFuture -> {
            if (!channelFuture.isSuccess()) {
                failMessages.add(failMessages);
                return;
            }
        });
    }

    @Override
    public void retrySendAll() {
        if (failMessages.size() > 0) {
            while (true) {
                try {
                    send(failMessages.remove());
                } catch (NoSuchElementException ex) {
                    break;
                }
            }
        }
    }
}
