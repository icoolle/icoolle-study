package com.icoolle.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * 设置swaggerApi 文档，设置 dev test 环境开启 prod 环境就关闭了
 */
@Profile(value = {"local", "dev", "test", "prod"})
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    final String BASE_PACKAGES = "com.icoolle";

    final String[][] MODEL_PACKAGES = {
            {"", "所有相关模块"},
            {".consumer.pms", "商品信息模块"},
            {".consumer.sms", "系统信息模块"},
            {".consumer.ums", "用户信息模块"},
    };

    @Bean
    public Docket docketAll() {
        return createRestApi(0);
    }

    @Bean
    public Docket docket01() {
        return createRestApi(1);
    }

    @Bean
    public Docket docket02() {
        return createRestApi(2);
    }

    @Bean
    public Docket docket03() {
        return createRestApi(3);
    }


    public Docket createRestApi(int i) {
        String s = MODEL_PACKAGES[i][1];
        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8082/")
                .pathMapping("/")
                .apiInfo(apiInfo(s))
                .groupName(s)
                .select()
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGES + MODEL_PACKAGES[i][0]))
                .paths(PathSelectors.any())
                .build().securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));
    }

    /**
     * 配置接口信息
     *
     * @param title
     * @return
     */
    private ApiInfo apiInfo(String title) {
        return new ApiInfoBuilder()
                .title(title)
                .description(title)
                .termsOfServiceUrl("xxxx.com")
                .contact(new Contact("YY", "xxx.com", "666@qq.com"))
                .version("1.0.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
    }
}