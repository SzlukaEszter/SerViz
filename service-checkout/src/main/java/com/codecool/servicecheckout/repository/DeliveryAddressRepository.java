package com.codecool.servicecheckout.repository;

import com.codecool.servicecheckout.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DeliveryAddressRepository extends JpaRepository <DeliveryAddress, Long> {
     DeliveryAddress findDeliveryAddressByUserIdAndActive(Long id, Boolean active);
     List<DeliveryAddress> findAllByUserId(Long userId);

     @Query("UPDATE DeliveryAddress d " +
             "set d.active = false " +
             "where d.active = true ")
     @Modifying(clearAutomatically = true)
     int setActiveFalse();

     @Query("UPDATE DeliveryAddress d " +
             "set d.active = true " +
             "where d.id = :id")
     @Modifying(clearAutomatically = true)
     int setAddressActive(@Param("id") Long id);
}