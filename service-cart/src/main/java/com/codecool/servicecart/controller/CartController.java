package com.codecool.servicecart.controller;


import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.service.CartService;
import com.codecool.servicecart.service.UserIdentifierService;
import io.swagger.annotations.ApiOperation;
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


    @GetMapping("/lineItems/{userId}")
    @ApiOperation("Get all lineItems in cart by userId")
    public List<LineItem> getAllLineItemInCart(@PathVariable Long userId) {
        return cartService.getAllLineItemByUserId(userId);
    }

    @GetMapping("/getCart/{userId}")
    @ApiOperation("Get cart by userId")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/add/{waterId}/{userId}")
    @ApiOperation("Add water to cart by userId")
    public void addToCart(@PathVariable("waterId") Long waterId, @PathVariable Long userId) {
        cartService.addToCart(waterId, userId);
    }

    @DeleteMapping("/emptyCart/{userId}")
    @ApiOperation("Remove all lineItems from cart by userId -- NOT WORKING VERSION")
    public void emptyCart(@PathVariable Long userId) {
        cartService.deleteAllLineItemFromCart(userId);
    }

    @PostMapping("/{userId}")
    @ApiOperation("Create new cart by userId")
    public void createNewCart(@PathVariable Long userId) {
        cartService.createCart(userId);
    }

}
