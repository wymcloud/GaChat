package com.wym.imnettyservice.nettyserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wym
 * @create 2018-11-28 16:37
 **/
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyConfig {

    private int port;


}
