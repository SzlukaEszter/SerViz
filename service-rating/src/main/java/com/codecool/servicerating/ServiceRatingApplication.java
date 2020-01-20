package com.codecool.servicerating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class ServiceRatingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRatingApplication.class, args);
    }

}
