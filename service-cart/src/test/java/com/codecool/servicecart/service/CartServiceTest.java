package com.codecool.servicecart.service;

import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.repository.CartRepository;
import com.codecool.servicecart.repository.LineItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
                .build();
        cart1 = cartRepository.save(cart1);

        Cart cart2 = Cart.builder()
                .userId(2L)
                .build();
        cart2 = cartRepository.save(cart2);

        LineItem holyWater = LineItem.builder()
                .name("HolyWater")
                .quantity(1)
                .price(20L)
                .cart(cart1)
                .build();
        lineItemRepository.save(holyWater);

        LineItem rakoskereszturiCsapviz = LineItem.builder()
                .name("Rákoskeresztúri rákvíz")
                .quantity(2)
                .price(10L)
                .cart(cart1)
                .build();
        lineItemRepository.save(rakoskereszturiCsapviz);

        LineItem jana = LineItem.builder()
                .name("Jana baby")
                .quantity(3)
                .price(20L)
                .cart(cart2)
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
                .build();
        cart1 = cartRepository.save(cart1);

        Cart cart2 = Cart.builder()
                .userId(2L)
                .build();
        cart2 = cartRepository.save(cart2);

        LineItem holyWater = LineItem.builder()
                .name("HolyWater")
                .quantity(1)
                .price(20L)
                .cart(cart1)
                .build();
        lineItemRepository.save(holyWater);

        LineItem rakoskereszturiCsapviz = LineItem.builder()
                .name("Rákoskeresztúri rákvíz")
                .quantity(2)
                .price(10L)
                .cart(cart1)
                .build();
        lineItemRepository.save(rakoskereszturiCsapviz);

        LineItem jana = LineItem.builder()
                .name("Jana baby")
                .quantity(3)
                .price(20L)
                .cart(cart2)
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

        assertThat(cart)
                .isNotNull()
                .matches(c -> c.getSum() == 0L);
    }

    @Test
    void getCart() {
        Cart cart1 = Cart.builder()
                .userId(1L)
                .build();
        cart1 = cartRepository.save(cart1);

        Cart cart2 = Cart.builder()
                .userId(2L)
                .build();
        cart2 = cartRepository.save(cart2);

        LineItem holyWater = LineItem.builder()
                .name("HolyWater")
                .quantity(1)
                .price(20L)
                .cart(cart1)
                .build();
        lineItemRepository.save(holyWater);

        LineItem rakoskereszturiCsapviz = LineItem.builder()
                .name("Rákoskeresztúri rákvíz")
                .quantity(2)
                .price(10L)
                .cart(cart1)
                .build();
        lineItemRepository.save(rakoskereszturiCsapviz);

        LineItem jana = LineItem.builder()
                .name("Jana baby")
                .quantity(3)
                .price(20L)
                .cart(cart2)
                .build();
        lineItemRepository.save(jana);

        final Cart cart = cartService.getCart(1L);

        assertThat(cart)
                .isNotNull()
                .matches(c -> c.getUserId() == 1L, "userid is 1")
                .matches(c -> c.getLineItems().size() == 2, "2 lineitems")
                .matches(c -> c.getSum() == 40L, "sum is 40");


    }

    @Test
    void increaseQuantity() {
        Cart cart1 = Cart.builder()
                .userId(1L)
                .build();
        cart1 = cartRepository.save(cart1);

        LineItem holyWater = LineItem.builder()
                .name("HolyWater")
                .quantity(1)
                .price(20L)
                .cart(cart1)
                .build();
        holyWater = lineItemRepository.save(holyWater);

        LineItem rakoskereszturiCsapviz = LineItem.builder()
                .name("Rákoskeresztúri rákvíz")
                .quantity(2)
                .price(30L)
                .cart(cart1)
                .build();
        lineItemRepository.save(rakoskereszturiCsapviz);


        cartService.increaseQuantity(holyWater.getId());


        Cart cart = cartService.getCart(cart1.getUserId());
        assertThat(cart).matches(c -> c.getSum() == 100L, "sum is 100");

    }

    @Test
    void reduceQuantityTo0_lineitemDisappears() {
        Cart cart1 = Cart.builder()
                .userId(1L)
                .build();
        cart1 = cartRepository.save(cart1);

        LineItem holyWater = LineItem.builder()
                .name("HolyWater")
                .quantity(1)
                .price(20L)
                .cart(cart1)
                .build();
        holyWater = lineItemRepository.save(holyWater);

        LineItem rakoskereszturiCsapviz = LineItem.builder()
                .name("Rákoskeresztúri rákvíz")
                .quantity(2)
                .price(30L)
                .cart(cart1)
                .build();
        lineItemRepository.save(rakoskereszturiCsapviz);


        cartService.reduceQuantity(holyWater.getId());


        Cart cart = cartService.getCart(cart1.getUserId());
        assertThat(cart)
                .matches(c -> c.getLineItems().size() == 1, "1 lineitem remains")
                .matches(c -> c.getSum() == 60L, "sum is 100");

    }

    @Test
    void reduceQuantity_lineitemDecreases() {
        Cart cart1 = Cart.builder()
                .userId(1L)
                .build();
        cart1 = cartRepository.save(cart1);

        LineItem holyWater = LineItem.builder()
                .name("HolyWater")
                .quantity(1)
                .price(20L)
                .cart(cart1)
                .build();
        holyWater = lineItemRepository.save(holyWater);

        LineItem rakoskereszturiCsapviz = LineItem.builder()
                .name("Rákoskeresztúri rákvíz")
                .quantity(2)
                .price(30L)
                .cart(cart1)
                .build();
        rakoskereszturiCsapviz = lineItemRepository.save(rakoskereszturiCsapviz);


        cartService.reduceQuantity(rakoskereszturiCsapviz.getId());


        Cart cart = cartService.getCart(cart1.getUserId());
        assertThat(cart)
                .matches(c -> c.getLineItems().size() == 2, "2 lineitem remains")
                .matches(c -> c.getSum() == 50L, "sum is 50");

    }

    @Test
    void deleteLineItem() {
        Cart cart1 = Cart.builder()
                .userId(1L)
                .build();
        cart1 = cartRepository.save(cart1);

        LineItem holyWater = LineItem.builder()
                .name("HolyWater")
                .quantity(1)
                .price(20L)
                .cart(cart1)
                .build();
        holyWater = lineItemRepository.save(holyWater);

        LineItem rakoskereszturiCsapviz = LineItem.builder()
                .name("Rákoskeresztúri rákvíz")
                .quantity(2)
                .price(30L)
                .cart(cart1)
                .build();
        rakoskereszturiCsapviz = lineItemRepository.save(rakoskereszturiCsapviz);


        cartService.deleteLineItem(rakoskereszturiCsapviz.getId());


        Cart cart = cartService.getCart(cart1.getUserId());

        assertThat(cart)
                .matches(c -> c.getLineItems().size() == 1, "1 lineitem remains");

    }

}