package com.codecool.servicerating.controller;

import com.codecool.servicerating.entity.Rating;
import com.codecool.servicerating.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping("/{id}")
    //public List<Rating> getAllRatingsForWater(@RequestParam("id") Integer waterId) {
    public List<Rating> getAllRatingsForWater(@PathVariable(value="id") Long waterId) {
        return ratingService.getRatingForWater(waterId);
    }

    @GetMapping("/all")
    public List<Rating> getAllRating() {
        return ratingService.getAllRating();
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