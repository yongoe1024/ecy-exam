package com.yongoe.exam.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 *
 * @author yongoe
 * @since 2023/1/1
 */
@Configuration
public class SwaggerConfig {


//    /**
//     * 配置分组
//     */
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("springdoc-hello")
//                .pathsToMatch("/**")
//                .build();
//    }

    /**
     * 配置文档信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("exam系统API")
                .version("1.0")
                .description("exam管理系统"));

    }

}