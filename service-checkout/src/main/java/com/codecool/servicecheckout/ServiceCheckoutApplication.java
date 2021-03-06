package com.codecool.servicecheckout;

import com.codecool.servicecheckout.model.DeliveryAddress;
import com.codecool.servicecheckout.repository.DeliveryAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class ServiceCheckoutApplication {

@Autowired
	DeliveryAddressRepository deliveryAddressRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServiceCheckoutApplication.class, args);
	}


	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public CommandLineRunner init() {
		return args -> {
			DeliveryAddress first = DeliveryAddress.builder()
					.active(true)
					.address("Nagymező u. 44.")
					.city("Budapest")
					.country("Hungary")
					.postalcode("1056")
					.userId(2L)
					.build();

			DeliveryAddress second = DeliveryAddress.builder()
					.active(true)
					.address("Béke u. 8.")
					.city("Debrecen")
					.country("Hungary")
					.postalcode("4226")
					.userId(1L)
					.build();

			DeliveryAddress third = DeliveryAddress.builder()
					.active(false)
					.address("Bálna u. 8.")
					.city("Nyíregyháza")
					.country("Hungary")
					.postalcode("3326")
					.userId(1L)
					.build();

			System.out.println(first);
			System.out.println(second);

			deliveryAddressRepository.saveAll(Arrays.asList(first, second, third));
		};
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public SimpleMailMessage templateSimpleMessage() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText(
				"Dear %1$s,\n\nWe received from you the folllowing order:\n\n %2$s" );
		message.setSubject("Thanks for your order!");
		return message;
	}

}