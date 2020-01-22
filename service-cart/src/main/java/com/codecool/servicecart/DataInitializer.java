package com.codecool.servicecart;


import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import com.codecool.servicecart.repository.CartRepository;
import com.codecool.servicecart.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    LineItemRepository lineItemRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public void run(String... args) throws Exception {
        Cart cart1 = Cart.builder()
                .id(1L)
                .lineItems(new ArrayList<LineItem>())
                .sum(new BigDecimal(0))
                .build();
        cartRepository.save(cart1);

        LineItem holyWater = LineItem.builder()
                .name("HolyWater")
                .quantity(1)
                .price(new BigDecimal(20))
                .cart(cartRepository.findAll().get(0))
                .lineItemSumPrice(new BigDecimal(20))
                .build();
        lineItemRepository.save(holyWater);

        LineItem rakoskereszturiCsapviz = LineItem.builder()
                .name("Rákoskeresztúri rákvíz")
                .quantity(2)
                .price(new BigDecimal(10))
                .cart(cartRepository.findAll().get(0))
                .lineItemSumPrice(new BigDecimal(20))
                .build();
        lineItemRepository.save(rakoskereszturiCsapviz);

        LineItem jana = LineItem.builder()
                .name("Jana baby")
                .quantity(3)
                .price(new BigDecimal(20))
                .cart(cartRepository.findAll().get(0))
                .lineItemSumPrice(new BigDecimal(60))
                .build();
        lineItemRepository.save(jana);
    }

}
