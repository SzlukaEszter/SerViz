package com.codecool.servicecheckout.repository;

import com.codecool.servicecheckout.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressRepository extends JpaRepository <DeliveryAddress, Long> {
}