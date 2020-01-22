package com.codecool.servicewater.controller;

import com.codecool.servicewater.model.RatingResult;
import com.codecool.servicewater.model.Water;
import com.codecool.servicewater.model.WaterWithRatings;
import com.codecool.servicewater.service.RatingServiceCaller;
import com.codecool.servicewater.service.WaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/water")
@CrossOrigin
public class WaterController {

    @Autowired
    WaterService waterService;

    @Autowired
    RatingServiceCaller ratingServiceCaller;

    @GetMapping("/all")
    public List<Water> getAllWater() {

        return waterService.getAllWater();

    }

    @GetMapping("/{id}")
    public WaterWithRatings getWaterById(@PathVariable Long id) {

        Water water = waterService.getWaterById(id);
        List<RatingResult> ratingResults = ratingServiceCaller.getRatingsByWaterId(id);
        return new WaterWithRatings(water, ratingResults);
    }

    @PostMapping("/add")
    public void addNewWater(@RequestBody Water water) {

        waterService.addNewWater(water);
    }

}
