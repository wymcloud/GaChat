package com.gachat.webservice.websocket;

import com.gachat.webservice.nettyclient.client.NettyClient;
import com.gachat.webservice.nettyclient.config.NettyClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/websocket")
@Component
public class ImWebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<ImWebSocket> webSocketSet = new CopyOnWriteArraySet<ImWebSocket>();

    private static CopyOnWriteArraySet<Session> sessionList = new CopyOnWriteArraySet<Session>();

    private static ConcurrentHashMap<String, ImWebSocket> webSocketMap = new ConcurrentHashMap<String, ImWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private static Session session;


    @Autowired
    private NettyClientConfig nettyConfig;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        sessionList.add(session);
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        NettyClient nettyClient = new NettyClient("127.0.0.1", 8082);
        nettyClient.send(message);
        System.out.println("来自客户端的消息:" + message);
    }

    /**
     * 向客户端发送消息
     *
     * @param message
     * @throws IOException
     */
    public static void sendAllMessage(String message) throws IOException {
        for (Session session1 : sessionList) {
            session1.getBasicRemote().sendText(message);
        }
    }

    public void sendMessag1e(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

     @OnError
     public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
     }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ImWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ImWebSocket.onlineCount--;
    }
}
