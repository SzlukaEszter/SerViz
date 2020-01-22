package com.codecool.servicecart.controller;


import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.service.CartService;
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

    @GetMapping("/")
    public List<LineItem> getAllLineItemInCart() {
        return cartService.getAllLineItem();
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

    @PostMapping("/increaseQuantity/{lineItemId}")
    public void increaseQuantity(@PathVariable Long lineItemId) {
        cartService.increaseQuantity(lineItemId);
    }

    @PostMapping("/reduceQuantity/{lineItemId}")
    public void reduceQuantity(@PathVariable Long lineItemId){
        cartService.reduceQuantity(lineItemId);
    }

    @DeleteMapping("/{lineItemId}")
    public void deleteItem(@PathVariable("lineItemId") Long id){
        cartService.deleteLineItem(id);
    }
}
