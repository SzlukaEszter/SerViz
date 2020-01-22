package com.codecool.servicecart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WaterData {

    private Long waterId;

    private String description;

    private String name;

    private BigDecimal price;

    private String picture;

    private int rating;


}
