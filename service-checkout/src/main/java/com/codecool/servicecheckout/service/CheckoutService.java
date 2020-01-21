package com.codecool.servicecheckout.service;

import com.codecool.servicecheckout.model.DeliveryAddress;
import com.codecool.servicecheckout.model.Order;
import com.codecool.servicecheckout.repository.DeliveryAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CheckoutService {

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    CartCaller cartCaller;


    public Order getOrder(Long userId){

        return Order.builder()
                .deliveryAddress(deliveryAddressRepository.findDeliveryAddressByUserIdAndActive(userId, true))
                .cart(cartCaller.getCart(userId))
                .userId(userId)
                .build();


    }

    public List<DeliveryAddress> getAddressesOfUser(Long userId){
        return deliveryAddressRepository.findAllById(Arrays.asList(userId));
    }

    public void addDeliveryAddress(DeliveryAddress address) {
        deliveryAddressRepository.save(address);
    }


    public void setActive(Long id) {
        deliveryAddressRepository.setActiveFalse();
        deliveryAddressRepository.setAddressActive(id);
    }
}
