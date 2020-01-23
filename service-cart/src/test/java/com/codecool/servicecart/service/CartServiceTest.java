package com.codecool.servicecart.service;

import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.repository.CartRepository;
import com.codecool.servicecart.repository.LineItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    LineItemRepository lineItemRepository;

    @BeforeEach
    void setUp() {
        lineItemRepository.deleteAll();
        cartRepository.deleteAll();
    }


    @Test
    void getAllLineItemByUserId() {
        Cart cart1 = Cart.builder()
                .userId(1L)
                .lineItems(new ArrayList<LineItem>())
                .sum(new BigDecimal(0))
                .build();
        cart1 = cartRepository.save(cart1);

        Cart cart2 = Cart.builder()
                .userId(2L)
                .lineItems(new ArrayList<LineItem>())
                .sum(new BigDecimal(0))
                .build();
        cart2 = cartRepository.save(cart2);

        LineItem holyWater = LineItem.builder()
                .name("HolyWater")
                .quantity(1)
                .price(new BigDecimal(20))
                .cart(cart1)
                .lineItemSumPrice(new BigDecimal(20))
                .build();
        lineItemRepository.save(holyWater);

        LineItem rakoskereszturiCsapviz = LineItem.builder()
                .name("Rákoskeresztúri rákvíz")
                .quantity(2)
                .price(new BigDecimal(10))
                .cart(cart1)
                .lineItemSumPrice(new BigDecimal(20))
                .build();
        lineItemRepository.save(rakoskereszturiCsapviz);

        LineItem jana = LineItem.builder()
                .name("Jana baby")
                .quantity(3)
                .price(new BigDecimal(20))
                .cart(cart2)
                .lineItemSumPrice(new BigDecimal(60))
                .build();
        lineItemRepository.save(jana);

        final List<LineItem> allLineItems = cartService.getAllLineItemByUserId(1L);

        assertThat(allLineItems)
                .hasSize(2);
    }

    @Test
    void deleteAllFromCart() {
        Cart cart1 = Cart.builder()
                .userId(1L)
                .lineItems(new ArrayList<LineItem>())
                .sum(new BigDecimal(0))
                .build();
        cart1 = cartRepository.save(cart1);

        Cart cart2 = Cart.builder()
                .userId(2L)
                .lineItems(new ArrayList<LineItem>())
                .sum(new BigDecimal(0))
                .build();
        cart2 = cartRepository.save(cart2);

        LineItem holyWater = LineItem.builder()
                .name("HolyWater")
                .quantity(1)
                .price(new BigDecimal(20))
                .cart(cart1)
                .lineItemSumPrice(new BigDecimal(20))
                .build();
        lineItemRepository.save(holyWater);

        LineItem rakoskereszturiCsapviz = LineItem.builder()
                .name("Rákoskeresztúri rákvíz")
                .quantity(2)
                .price(new BigDecimal(10))
                .cart(cart1)
                .lineItemSumPrice(new BigDecimal(20))
                .build();
        lineItemRepository.save(rakoskereszturiCsapviz);

        LineItem jana = LineItem.builder()
                .name("Jana baby")
                .quantity(3)
                .price(new BigDecimal(20))
                .cart(cart2)
                .lineItemSumPrice(new BigDecimal(60))
                .build();
        lineItemRepository.save(jana);

        cartService.deleteAllLineItemFromCart(2L);

        final List<LineItem> allLineItems = cartService.getAllLineItemByUserId(1L);
        assertThat(allLineItems)
                .hasSize(2);
    }

    @Test
    void createCart() {
        cartService.createCart(3L);

        final Cart cart = cartService.getCart(3L);

        assertThat(cart).isNotNull();
    }


}