package com.gachat.webservice.service.iface;

/**
 * @author wym
 * @create 2019-01-24 12:45
 **/
public interface IUserPushService {

    /**
     *  给所有用户推送消息
     *
     * @param message
     */

    void pushAllUserMessage(String message);
}
