package com.sgwang.nio;

import com.sgwang.nio.client.Client;
import com.sgwang.nio.server.Server;

import java.util.Scanner;

/**
 * @创建人 sgwang
 * @name TestClientTwo
 * @user 91119
 * @创建时间 2019/7/28
 * @描述
 */
public class TestClientTwo {
    //测试主方法
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        //运行客户端
        Client.start();
        while (Client.sendMsg(new Scanner(System.in).nextLine())) ;
    }
}
