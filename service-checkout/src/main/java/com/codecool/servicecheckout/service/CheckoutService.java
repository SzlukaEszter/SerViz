package com.codecool.servicecheckout.service;

import com.codecool.servicecheckout.model.DeliveryAddress;
import com.codecool.servicecheckout.model.Order;
import com.codecool.servicecheckout.model.User;
import com.codecool.servicecheckout.repository.DeliveryAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CheckoutService {

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    CartCaller cartCaller;


    @Autowired
    UserCaller userCaller;

    public Order getOrder(Long userId) {

        return Order.builder()
                .deliveryAddress(deliveryAddressRepository.findDeliveryAddressByUserIdAndActive(userId, true))
                .cart(cartCaller.getCart(userId))
                .userId(userId)
                .build();


    }

    public List<DeliveryAddress> getAddressesOfUser(Long userId) {
        return deliveryAddressRepository.findAllByUserId(userId);
    }

    public void addDeliveryAddress(DeliveryAddress address) {
        deliveryAddressRepository.save(address);
    }

    @Transactional
    public void setActive(Long id) {
        deliveryAddressRepository.setActiveFalse();
        deliveryAddressRepository.setAddressActive(id);
    }

    @Autowired
    SimpleMailMessage template;

    @Autowired
    EmailService emailService;

    public void sendEmail(Long userId) {
        Order order = getOrder(userId);
        User user = userCaller.getUser(userId);
        String text = String.format(
                template.getText(),
                user.getUsername(),
                order.toString());

        emailService.sendSimpleMessage(
                user.getEmail(),
                template.getSubject(),
                text);
    }

    public void closeOrder(Long userId) {
        sendEmail(userId);

    }
}
