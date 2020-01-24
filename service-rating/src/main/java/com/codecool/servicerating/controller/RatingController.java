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

    @GetMapping("/{water_id}")
    public List<Rating> getAllRatingsForWater(@PathVariable(value="water_id") Long waterId) {
        return ratingService.getRatingForWater(waterId);
    }

    @GetMapping("/userrating/{user_id}")
    public List<Rating> getAllRatingsForUser(@PathVariable(value="user_id") Long userId) {
        return ratingService.getRatingForUser(userId);
    }

    //ESZTER
    @GetMapping("/valami")
    public String getString() {
        return "hailihaó";
    }

    @GetMapping("/all")
    public List<Rating> getAllRating() {
        return ratingService.getAllRating();
    }

    @DeleteMapping("/delete/{ratingId}")
    public void deleteRating(@PathVariable Long ratingId){
        ratingService.deleteRatingById(ratingId);
    }

    @PostMapping("/save/{waterId}")
    public void saveRating(@RequestBody Rating rating, @PathVariable Long waterId){
        ratingService.saveRating(rating, waterId);
    }
}