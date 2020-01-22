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
                .paths(PathSelectors.ant("/rating/**"))
                .build();
    }

    @Bean
    public CommandLineRunner init() {

        return args -> {

            Rating rating1  = Rating.builder()
                    .comment("lol funny taste")
                    .rating(5)
                    .waterId((long) 1)
                    .build();

            Rating rating2  = Rating.builder()
                    .comment("weird taste, yellow color")
                    .rating(4)
                    .waterId((long) 2)
                    .build();

            Rating rating3  = Rating.builder()
                    .comment("it tastes like my granny, but still okay")
                    .rating(5)
                    .waterId((long) 3)
                    .build();

            Rating rating4  = Rating.builder()
                    .comment("nothing better, than water that taste like old people")
                    .rating(5)
                    .waterId((long) 3)
                    .build();

            ratingRepository.save(rating1);
            ratingRepository.save(rating2);
            ratingRepository.save(rating3);
            ratingRepository.save(rating4);
        };
    }
}
