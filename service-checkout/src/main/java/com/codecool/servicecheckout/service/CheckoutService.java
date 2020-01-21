package com.codecool.servicecheckout.service;

import com.codecool.servicecheckout.model.DeliveryAddress;
import com.codecool.servicecheckout.repository.DeliveryAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class CheckoutService {

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddress getOrder(Long userId){
        return deliveryAddressRepository.findDeliveryAddressByUserIdAndActive(userId, true);
    }
}
