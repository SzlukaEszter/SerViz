package com.codecool.servicecart.controller;


import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.model.WaterData;
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
    public List<LineItem> getAllLineItemInCart(){
        return cartService.getAllLineItem();
    }

    @GetMapping("/getCart")
    public Cart getCart(@RequestParam Long userId){
        return cartService.getCart(userId);
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody WaterData water){
        cartService.addToCart(water);
    }

    @DeleteMapping("/deleteAll")
    public void emptyCart(){
        cartService.deleteAllLineItemFromCart();
    }

    @PostMapping("/increaseQuantity/{id}")
    public void increaseQuantity(@PathVariable Long id){
        cartService.increaseQuantity(id);
    }

    @PostMapping("/reduceQuantity/{id}")
    public void reduceQuantity(@PathVariable Long id){
        cartService.reduceQuantity(id);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Long id){
        cartService.deleteLineItem(id);
    }
}