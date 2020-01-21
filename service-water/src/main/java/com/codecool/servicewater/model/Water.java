package com.codecool.servicewater.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Water {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private BigDecimal price;
    private String description;
    private String picture;
    private int rating;

}
