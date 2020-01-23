package com.codecool.servicecart.service;


import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.model.WaterData;
import com.codecool.servicecart.repository.CartRepository;
import com.codecool.servicecart.repository.LineItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService {

    @Autowired
    private LineItemRepository lineItemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    WaterCaller waterCaller;


    public List<LineItem> getAllLineItemByUserId(Long userId) {
        Cart cart = getCart(userId);
        return lineItemRepository.findAllByCart(cart);
    }

    @Transactional
    public void deleteAllLineItemFromCart(Long userId) {
        Cart cart = getCart(userId);
        lineItemRepository.deleteAllByCart(cart);
    }

    public Cart getCart(Long userId) {
        Optional<Cart> actualCart = cartRepository.findByUserId(userId);
        if (!actualCart.isPresent()) {
            throw new RuntimeException("Cart not found");
        }
        return actualCart.get();
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

    @Transactional
    public void increaseQuantity(Long lineItemId) {
        LineItem lineItem = lineItemRepository.findById(lineItemId).get();
        lineItem.setQuantity(lineItem.getQuantity() + 1);
        calculateSumOfLineItems(lineItem);
    }

    @Transactional
    public void reduceQuantity(Long lineItemId) {
        LineItem lineItem = lineItemRepository.findById(lineItemId).get();
        lineItem.setQuantity(lineItem.getQuantity() - 1);
        calculateSumOfLineItems(lineItem);
        // check if quantity = 0 -> delete lineItem
        if (lineItem.getQuantity() == 0) {
            lineItemRepository.delete(lineItem);
        }
    }

    public void deleteLineItem(Long lineItemId) {
        LineItem lineItem = lineItemRepository.findLineItemById(lineItemId);
        lineItemRepository.delete(lineItem);
    }

    public void addToCart(Long waterId, Long userId) {
        Cart cart = getCart(userId);
        WaterData water = waterCaller.getWater(waterId);

        if (cart.getLineItems().stream().anyMatch(lineItem -> lineItem.getName().contentEquals(water.getName()))) {
            throw new RuntimeException("This item already in your cart!");
        } else {
            LineItem lineItem = LineItem.builder()
                    .name(water.getName())
                    .price(water.getPrice())
                    .quantity(1)
                    .lineItemSumPrice(water.getPrice())
                    .cart(cart)
                    .build();
            lineItemRepository.save(lineItem);
        }
    }

    public void createCart(Long userId) {
        Cart cart = Cart.builder().userId(userId).build();
        cartRepository.save(cart);
    }
}