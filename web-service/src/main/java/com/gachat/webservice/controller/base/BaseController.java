package com.gachat.webservice.controller.base;

import com.gachat.webservice.service.iface.IUserPushService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.rmi.PortableRemoteObject;

/**
 * @author wym
 * @create 2019-01-24 12:49
 **/
public class BaseController {

    @Autowired
    protected IUserPushService userPushService;
}
