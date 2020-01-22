package com.codecool.servicecart.repository;

import com.codecool.servicecart.model.Cart;
import com.codecool.servicecart.model.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {

    LineItem findLineItemById(Long Id);

    List<LineItem> findAllByCart(Cart cart);

    void deleteAllByCart(Cart cart);

}
