package com.codecool.servicerating.controller;

import com.codecool.servicerating.entity.Rating;
import com.codecool.servicerating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping("/")
    public List<Rating> getAllRatingsForWater(@RequestParam("id") Long waterId) {
        return ratingService.getRatingForWater(waterId);
    }

    @DeleteMapping("/")
    public void deleteRating(@RequestBody Rating rating){
        ratingService.deleteRating(rating);
    }

    @PostMapping("/")
    public void saveRating(@RequestBody Rating rating){
        ratingService.saveRating(rating);
    }
}