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



}