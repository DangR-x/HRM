package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/3 18:58
 * @Version v1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class FastdfsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastdfsServiceApplication.class,args);
    }

}
