package com.sgwang.restTemplate.boot.control;

import com.sgwang.restTemplate.boot.domain.AliasClass;
import com.sgwang.restTemplate.boot.domain.User;
import com.sgwang.restTemplate.boot.exception.SimpleHttpException;
import com.sgwang.restTemplate.boot.service.UserService;
import com.sgwang.restTemplate.boot.tool.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @创建人 sgwang
 * @name TestController
 * @user shiguang.wang
 * @创建时间 2019/7/8
 * @描述
 */
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/list")
    public List<User> list() {
        System.out.println("执行list----------");
        List<User> users = userService.findAll();

        return userService.findAll();
    }

    @GetMapping("/test")
    public Payload test() {
        System.out.println("----------执行test----------");

        ClientHttpRequestFactory test = restTemplate.getRequestFactory();
        System.out.println("SimpleClientHttpRequestFactory: " + (test instanceof SimpleClientHttpRequestFactory));
        System.out.println("HttpComponentsClientHttpRequestFactory: " + (test instanceof HttpComponentsClientHttpRequestFactory));

        Payload payload = null;

        for (int index = 0; index < 20; index++) {
            try {
                payload = restTemplate.getForObject("http://localhost:8090/class", Payload.class);

                System.out.println("payload: " + payload.toString());
            }catch (SimpleHttpException exception){
                System.out.println("index: " + index + ",   exception: " + exception.getMessage());
            }
        }

        return payload;
    }
}
