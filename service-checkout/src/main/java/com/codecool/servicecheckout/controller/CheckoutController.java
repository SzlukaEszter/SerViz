package com.codecool.servicecheckout.controller;

import com.codecool.servicecheckout.model.DeliveryAddress;
import com.codecool.servicecheckout.model.Order;
import com.codecool.servicecheckout.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/checkout")
public class CheckoutController {

@Autowired
CheckoutService checkoutService;

    @GetMapping("/")
    public Order getOrder(@RequestParam("userId") Long userId){
        DeliveryAddress deliveryAddress = checkoutService.getOrder(userId);
        // todo get Cart with restTemplate and build Order to return
        return Order.builder()
                .deliveryAddress(deliveryAddress)
                .userId(userId)
                .build();

    }

}
