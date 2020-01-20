package com.codecool.servicewater;

import com.codecool.servicewater.model.Water;
import com.codecool.servicewater.repository.WaterRepository;
import com.codecool.servicewater.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

@SpringBootApplication
@EnableSwagger2
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
