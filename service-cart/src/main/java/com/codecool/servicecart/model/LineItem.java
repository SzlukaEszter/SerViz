package com.codecool.servicecart.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int quantity;

    private Long price;

    @Transient
    private Long lineItemSumPrice;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Cart cart;

    public Long getLineItemSumPrice() {
        return ((long) getQuantity()) * getPrice();

    }
}
