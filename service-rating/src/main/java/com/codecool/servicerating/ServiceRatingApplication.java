package com.codecool.servicerating;

import com.codecool.servicerating.entity.Rating;
import com.codecool.servicerating.repository.RatingRepository;
import com.codecool.servicerating.service.RatingService;
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
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class ServiceRatingApplication {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    RatingService ratingService;

    public static void main(String[] args) {
        SpringApplication.run(ServiceRatingApplication.class, args);
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

            Rating rating1  = Rating.builder()
                    .comment("lol funny taste")
                    .id(1)
                    .rating(5)
                    .waterId(1)
                    .build();

            Rating rating2  = Rating.builder()
                    .comment("weird taste, yellow color")
                    .id(2)
                    .rating(4)
                    .waterId(2)
                    .build();

            Rating rating3  = Rating.builder()
                    .comment("it tastes like my granny, but still okay")
                    .id(3)
                    .rating(5)
                    .waterId( 3)
                    .build();

            ratingRepository.save(rating1);
            ratingRepository.save(rating2);
            ratingRepository.save(rating3);
        };
    }
}
