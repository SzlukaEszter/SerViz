package com.codecool.servicewater;

import com.codecool.servicewater.model.Water;
import com.codecool.servicewater.repository.WaterRepository;
import com.codecool.servicewater.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class ServiceWaterApplication {

    @Autowired
    WaterRepository waterRepository;

    @Autowired
    WaterService waterService;

    public static void main(String[] args) {
        SpringApplication.run(ServiceWaterApplication.class, args);
    }

    @Bean
    public Docket api() {
        return  new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/water/**"))
                .build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @PostConstruct
    public CommandLineRunner init() {

        return args -> {

            Water water1 = Water.builder()
                    .name("Tündérvíz")
                    .price(new BigDecimal(499))
                    .description("Legfinomabb víz, tündéri ízek")
                    .rating(5)
                    .build();

            Water water2 = Water.builder()
                    .name("Jana")
                    .price(new BigDecimal(199))
                    .description("Nem rossz víz, remek ízek")
                    .rating(5)
                    .build();

            Water water3 = Water.builder()
                    .name("Szentkirályi")
                    .price(new BigDecimal(99))
                    .description("Borzasztó víz, kellemetlen ízek")
                    .rating(5)
                    .build();

            waterRepository.saveAndFlush(water1);
            waterRepository.saveAndFlush(water2);
            waterRepository.saveAndFlush(water3);
        };
    }

}
