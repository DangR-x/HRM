package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/5 11:20
 * @Version v1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class PageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageServiceApplication.class,args);
    }

}
