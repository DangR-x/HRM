package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/7 14:17
 * @Version v1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class EsServieceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsServieceApplication.class,args);
    }
}
