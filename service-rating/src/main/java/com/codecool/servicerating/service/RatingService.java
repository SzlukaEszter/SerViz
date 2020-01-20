package com.codecool.servicerating.service;


import com.codecool.servicerating.entity.Rating;
import com.codecool.servicerating.repository.RatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public List<Rating> getRatingForWater(Integer waterId) {
        return ratingRepository.findAllByWaterId(waterId);
    }

    public void deleteRating(Rating rating) {
        ratingRepository.delete(rating);

    }
}
