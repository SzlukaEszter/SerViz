package com.codecool.servicecheckout.service;

import com.codecool.servicecheckout.model.DeliveryAddress;
import com.codecool.servicecheckout.repository.DeliveryAddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CheckoutServiceTest {
@Autowired
CheckoutService checkoutService;

@Autowired
    DeliveryAddressRepository deliveryAddressRepository;

@BeforeEach
void cleardb(){
    deliveryAddressRepository.deleteAll();
}

    @Test
    void getOrder() {
        DeliveryAddress first = DeliveryAddress.builder()
                .active(true)
                .address("Nagymező u. 44.")
                .city("Budapest")
                .country("Hungary")
                .postalcode("1056")
                .userId(1L)
                .build();

        DeliveryAddress second = DeliveryAddress.builder()
                .active(false)
                .address("Béke u. 8.")
                .city("Debrecen")
                .country("Hungary")
                .postalcode("4226")
                .userId(1L)
                .build();

        deliveryAddressRepository.saveAll(Arrays.asList(first, second));
        DeliveryAddress address = checkoutService.getOrder(1L);
        assertThat(address)
                .matches(DeliveryAddress::isActive);




    }
}