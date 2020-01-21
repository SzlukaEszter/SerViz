package com.codecool.servicewater.controller;

import com.codecool.servicewater.model.Water;
import com.codecool.servicewater.service.WaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/water")
public class WaterController {

    @Autowired
    WaterService waterService;

    @GetMapping("/all")
    public List<Water> getAllWater() {

        return waterService.getAllWater();

    }

    @GetMapping("/{id}")
    public Water getWaterById(@RequestParam Long id) {

        return waterService.getWaterById(id);
    }

    @PostMapping("/add")
    public void addNewWater(@RequestBody Water water) {

        waterService.addNewWater(water);
    }
}
