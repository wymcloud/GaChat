package com.gachat.webservice.service.base;

import com.gachat.webservice.nettyclient.config.NettyClientConfig;

import javax.annotation.Resource;

/**
 * @author wym
 * @create 2019-01-24 11:44
 **/
public class BaseService {

    @Resource
    protected NettyClientConfig nettyClientConfig;

}
