package com.codecool.servicewater.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaterWithRatings {

    private Water water;

    private List<RatingResult> ratings;
}
