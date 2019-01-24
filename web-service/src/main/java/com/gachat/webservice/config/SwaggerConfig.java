package com.gachat.webservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.groupName("v1.0");
        docket.genericModelSubstitutes(DeferredResult.class);
        docket.useDefaultResponseMessages(false);
        docket.select().apis(RequestHandlerSelectors.basePackage("com.gachat.webservice.controller"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
        return docket;
    }

    private ApiInfo apiInfo() {
        ApiInfoBuilder builder =   new ApiInfoBuilder();
        builder.title("对外开放接口API 文档");
        builder.description("HTTP对外开放接口");
        builder.version("1.0.0");
        builder.termsOfServiceUrl("http://xxx.xxx.com");
        builder.license("LICENSE");
        builder.licenseUrl("http://xxx.xxx.com");
        return builder.build();
    }
}
