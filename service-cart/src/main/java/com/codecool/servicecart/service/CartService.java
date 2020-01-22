package com.codecool.servicecart.service;


import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.model.WaterData;
import com.codecool.servicecart.repository.CartRepository;
import com.codecool.servicecart.repository.LineItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
public class CartService {

    @Autowired
    private LineItemRepository lineItemRepository;

    @Autowired
    CartRepository cartRepository;

    public List<LineItem> getAllLineItem(){
        return lineItemRepository.findAll();
    }

    public void deleteAllLineItemFromCart(){
        lineItemRepository.deleteAll();
    }

    public Cart getCart(Long userId){
        return cartRepository.findById(userId).get();
    }

    public int calculateSumOfLineItems(LineItem lineItem){
        return lineItem.getQuantity() * lineItem.getPrice().intValue();
    }

    public void increaseQuantity(Long lineItemId){
        LineItem lineItem = lineItemRepository.findById(lineItemId).get();
        lineItem.setQuantity(lineItem.getQuantity()+1);
        this.calculateSumOfLineItems(lineItem);
    }

    public void reduceQuantity(Long lineItemId){
        LineItem lineItem = lineItemRepository.findById(lineItemId).get();
        lineItem.setQuantity(lineItem.getQuantity()-1);
        this.calculateSumOfLineItems(lineItem);
    }

    public void deleteLineItem(Long lineItemId){
        LineItem lineItem = lineItemRepository.findLineItemById(lineItemId);
        lineItemRepository.delete(lineItem);
    }

    public void addToCart(WaterData water){
        LineItem linei = LineItem.builder()
                .name(water.getName())
                .price(water.getPrice())
                .quantity(1)
                .lineItemSumPrice(water.getPrice())
                .build();
        lineItemRepository.save(linei);
    }
}
