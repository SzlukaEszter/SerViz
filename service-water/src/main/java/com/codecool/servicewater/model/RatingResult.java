package com.codecool.servicewater.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Data
public class RatingResult {

    private Long id;

    private String comment;

    private Integer rating;

    private Long waterId;
}
