package boot.control;

import boot.domain.AliasClass;
import boot.domain.Student;
import boot.domain.User;
import boot.service.UserService;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.remoting.support.SimpleHttpServerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

        for (User user : users) {
            System.out.println(user.getName());
        }

        return userService.findAll();
    }

    @GetMapping("/test")
    public void test() {
        System.out.println("----------执行test----------");

        ClientHttpRequestFactory test = restTemplate.getRequestFactory();
        System.out.println("SimpleClientHttpRequestFactory: " + (test instanceof SimpleClientHttpRequestFactory));
        System.out.println("HttpComponentsClientHttpRequestFactory: " + (test instanceof HttpComponentsClientHttpRequestFactory));

        try {
            for (int index = 0; index < 20; index++) {
                AliasClass aClass = restTemplate.getForObject("http://localhost:8090/class", AliasClass.class);
                System.out.println(Thread.currentThread() + " --> 执行index --> " + index);
            }
        } catch (HttpClientErrorException e) {
            System.out.println("http客户端请求出错了！");
            //开发中可以使用统一异常处理，或者在业务逻辑的catch中作响应
        }

    }
}
