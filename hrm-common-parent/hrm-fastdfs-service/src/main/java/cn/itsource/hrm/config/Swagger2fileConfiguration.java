package cn.itsource.hrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2019/12/26 15:42
 * @Version v1.0
 *
 * 因为没有注册进euraka中去，所以swagger需要单独配置
 *
 *
 */
@Configuration
public class Swagger2fileConfiguration {



    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //对外暴露服务的包,以controller的方式暴露,所以就是controller的包.
                .apis(RequestHandlerSelectors.basePackage("cn.itsource.hrm.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文件服务")
                .description("文件服务接口文档")
                .contact(new Contact("DangR-X", "", "1023056792@qq.com"))
                .version("1.01")
                .build();
    }
}
