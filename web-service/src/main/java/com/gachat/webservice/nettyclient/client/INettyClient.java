package com.gachat.webservice.nettyclient.client;


public interface INettyClient {

    /**
     *  初始化
     */
    void init();

    /**
     * 关闭
     */
    void destroy();

    /**
     * 发送消息
     *
     */
    void send(Object object);

    /**
     * 重发失败消息
     */
    void retrySendAll();
}
