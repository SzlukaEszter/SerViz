package com.codecool.servicecheckout.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LineItem {

    private Long id;
    private String name;
    private int quantity;
    private BigDecimal price;
    private BigDecimal lineItemSumPrice;
}
