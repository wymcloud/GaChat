package com.gachat.webservice.service.impl;

import com.gachat.webservice.nettyclient.client.NettyClient;
import com.gachat.webservice.service.base.BaseService;
import com.gachat.webservice.service.iface.IUserPushService;
import org.springframework.stereotype.Service;

/**
 * @author wym
 * @create 2019-01-24 12:45
 **/
@Service
public class UserPushServiceImpl extends BaseService implements IUserPushService {

    @Override
    public void pushAllUserMessage(String message) {
        NettyClient nettyClient = new NettyClient(nettyClientConfig.getHost(), nettyClientConfig.getPort());
        nettyClient.send(message);
    }

}
