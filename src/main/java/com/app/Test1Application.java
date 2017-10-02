package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication //(scanBasePackages= {"com.app.business", "com.app.entity"})
@EnableSwagger2
@EnableScheduling
public class Test1Application {

    public static void main(String[] args) {
            SpringApplication.run(Test1Application.class, args);
    }
        
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.app"))
                .apis(RequestHandlerSelectors.any())
                //.paths(regex("/customer/v1.*")) 
                .paths(PathSelectors.any()) 
                .build();
        
    }
}
