package com.codecool.servicecart;

import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.repository.CartRepository;
import com.codecool.servicecart.repository.LineItemRepository;
import com.codecool.servicecart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class ServiceCartApplication {

    @Autowired
    LineItemRepository lineItemRepository;

    @Autowired
    CartRepository cartRepository;

    public static void main(String[] args) {
        SpringApplication.run(ServiceCartApplication.class, args);
    }

}
