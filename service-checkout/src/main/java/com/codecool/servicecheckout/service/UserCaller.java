package com.codecool.servicecheckout.service;

import com.codecool.servicecheckout.model.Cart;
import com.codecool.servicecheckout.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserCaller {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-user.url}")
    private String userUrl;

    public User getUser(Long userId) {
        User response =
                restTemplate.getForObject(
                        userUrl + "/"+ userId,
                        User.class);
        return response;


    }

}
