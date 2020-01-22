package com.codecool.servicecheckout.controller;

import com.codecool.servicecheckout.model.DeliveryAddress;
import com.codecool.servicecheckout.model.Order;
import com.codecool.servicecheckout.service.CartCaller;
import com.codecool.servicecheckout.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/checkout")
public class CheckoutController {

@Autowired
CheckoutService checkoutService;

@Autowired
CartCaller cartCaller;

    @GetMapping("/")
    public Order getOrder(@RequestParam("userId") Long userId){
        return checkoutService.getOrder(userId);

    }

    @GetMapping("/marton")
    public String callMarton(){
        return cartCaller.callMarton();
    }

   @PostMapping("/getAllDeliveryAddress")
    public List<DeliveryAddress> getAllAddressesOfUser(@RequestParam("userId") Long userId){
        return checkoutService.getAddressesOfUser(userId);
   }

   @PostMapping("/add")
    public void addNewAddress(@RequestBody DeliveryAddress address) throws Exception {
        if (address.getId() != null) {throw new IllegalArgumentException("Address already egsist");}
        checkoutService.addDeliveryAddress(address);
   }

   @PutMapping("/update")
    public void updateAddress(@RequestBody DeliveryAddress address) throws IllegalArgumentException {
        if (address.getId() == null) {
            throw new IllegalArgumentException("Id not found!");
        }
        checkoutService.addDeliveryAddress(address);
   }

   @PutMapping("/set-active")
    public void setActive(@RequestParam("id") Long id){
        checkoutService.setActive(id);
   }



}


