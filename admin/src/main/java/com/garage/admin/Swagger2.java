/*
package com.garage.admin;

import org.springframework.context.annotation.Bean;

*/
/**
 * @Author: LiFan
 * @Date: 2020/2/21
 *//*


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

*/
/**
 * @ClassName: swagger2配置
 * @Description: TODO
 * @author 刘圈圈
 * @date 2018年7月5日
 *//*


@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.sr.modules.tra.app"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("旅游用车  APIs")
                .description("--------------------------------")
                .termsOfServiceUrl("https://blog.csdn.net/ityqing")
                .contact("刘圈圈")
                .version("0.1.1")
                .build();
    }

}
*/
