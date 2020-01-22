package com.codecool.servicecart.controller;


import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.service.CartService;
import com.codecool.servicecart.service.UserIdentifierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserIdentifierService userIdentifierService;

    @GetMapping("/")
    public List<LineItem> getAllLineItemInCart() {
        Long userId = userIdentifierService.getUserId();
        return cartService.getAllLineItemByUserId(userId);
    }

    @GetMapping("/getCart/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/add/{waterId}/{cartId}")
    public void addToCart(@PathVariable("waterId") Long waterId, @PathVariable("cartId") Long cartId) {
        cartService.addToCart(waterId, cartId);
    }

    @DeleteMapping("/deleteAll")
    public void emptyCart() {
        cartService.deleteAllLineItemFromCart();
    }

}
