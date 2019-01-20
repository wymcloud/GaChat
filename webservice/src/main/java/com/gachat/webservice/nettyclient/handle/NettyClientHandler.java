package com.gachat.webservice.nettyclient.handle;

import com.gachat.webservice.websocket.ImWebSocket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
        System.out.println("收到服务端消息: " + msg);
        //向websocket发送消息
        ImWebSocket.sendMessage(msg);
    }
}
