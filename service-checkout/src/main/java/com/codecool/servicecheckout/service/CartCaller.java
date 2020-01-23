package com.codecool.servicecheckout.service;

import com.codecool.servicecheckout.model.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CartCaller {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-rating.url}")
    private String ratingUrl;

    @Value("${service-cart.url}")
    private String cartUrl;


    public String callMarton() {
        ResponseEntity<String> response = restTemplate.getForEntity(ratingUrl + "/valami", String.class);
        return response.getBody();
    }

    public Cart getCart(Long userId) {
        Cart response =
                restTemplate.getForObject(
                        cartUrl + "/getCart/" + userId,
                        Cart.class);
        return response;
    }

    public void emptyCart(Long userId) {
        restTemplate.delete(cartUrl + "/emptyCart" + userId);
    }


}


