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

   @GetMapping("/{userId}/address")
    public List<DeliveryAddress> getAllAddressesOfUser(@PathVariable("userId") Long userId){
        return checkoutService.getAddressesOfUser(userId);
   }

   @PostMapping("/")
    public void addNewAddress(@RequestBody DeliveryAddress address) throws IllegalArgumentException {
        if (address.getId() != null) {throw new IllegalArgumentException("Address already exists");}
        checkoutService.addDeliveryAddress(address);
   }

   @PutMapping("/")
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


   @PostMapping("/{userId}/close")
    public void closeOrder(@PathVariable Long userId) {checkoutService.closeOrder(userId);}


}


