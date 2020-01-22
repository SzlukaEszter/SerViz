package com.codecool.servicecart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
public class WaterData {

    private String name;

    private BigDecimal price;

}
