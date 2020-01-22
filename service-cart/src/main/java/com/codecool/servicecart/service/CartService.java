package com.codecool.servicecart.service;


import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.model.WaterData;
import com.codecool.servicecart.repository.CartRepository;
import com.codecool.servicecart.repository.LineItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartService {

    @Autowired
    private LineItemRepository lineItemRepository;

    @Autowired
    CartRepository cartRepository;


    @Autowired
    WaterCaller waterCaller;


    public List<LineItem> getAllLineItem() {
        return lineItemRepository.findAll();
    }

    public void deleteAllLineItemFromCart() {
        lineItemRepository.deleteAll();
    }

    public Cart getCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public int calculateSumOfLineItems(LineItem lineItem) {
        return lineItem.getQuantity() * lineItem.getPrice().intValue();
    }


    public int calculateTotalCart(Cart cart) {
        List<LineItem> lineItems = cart.getLineItems();
        int total = 0;
        for (LineItem i : lineItems) {
            i.getLineItemSumPrice();
        }
        return total;
    }

    public void increaseQuantity(Long lineItemId) {
        LineItem lineItem = lineItemRepository.findById(lineItemId).get();
        lineItem.setQuantity(lineItem.getQuantity() + 1);
        calculateSumOfLineItems(lineItem);
    }

    public void reduceQuantity(Long lineItemId) {
        LineItem lineItem = lineItemRepository.findById(lineItemId).get();
        lineItem.setQuantity(lineItem.getQuantity() - 1);
        calculateSumOfLineItems(lineItem);
    }

    public void deleteLineItem(Long lineItemId) {
        LineItem lineItem = lineItemRepository.findLineItemById(lineItemId);
        lineItemRepository.delete(lineItem);
    }

    public void addToCart(Long waterId) {
        WaterData water = waterCaller.getWater(waterId);
        LineItem lineItem = LineItem.builder()
                .name(water.getName())
                .price(water.getPrice())
                .quantity(1)
                .lineItemSumPrice(water.getPrice())
                .build();
        lineItemRepository.save(lineItem);
    }
}
