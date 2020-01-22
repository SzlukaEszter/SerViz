package com.codecool.servicecart.controller;

import com.codecool.servicecart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/lineitem")
public class LineItemController {
    @Autowired
    private CartService cartService;

    @PutMapping("/increaseQuantity/{lineItemId}")
    public void increaseQuantity(@PathVariable Long lineItemId) {
        cartService.increaseQuantity(lineItemId);
    }

    @PutMapping("/reduceQuantity/{lineItemId}")
    public void reduceQuantity(@PathVariable Long lineItemId){
        cartService.reduceQuantity(lineItemId);
    }

    @DeleteMapping("/{lineItemId}")
    public void deleteItem(@PathVariable("lineItemId") Long id){
        cartService.deleteLineItem(id);
    }
}
