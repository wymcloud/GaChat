package com.wym.imnettyservice.nettyserver.handle;

import com.wym.imnettyservice.nettyserver.config.NettyConfig;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author wym
 * @create 2019-01-25 20:27
 **/
public class asd {

    public static void main(String[] args) {
        NettyConfig nettyConfig = new NettyConfig();
        System.out.println( Optional.ofNullable(nettyConfig).orElseGet(new NettyConfig()));

    }
}