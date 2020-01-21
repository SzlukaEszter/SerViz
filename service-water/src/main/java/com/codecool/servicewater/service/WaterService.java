package com.codecool.servicewater.service;

import com.codecool.servicewater.model.Water;
import com.codecool.servicewater.repository.WaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WaterService {

    @Autowired
    WaterRepository waterRepository;

    public List<Water> getAllWater() {

        return waterRepository.findAll();
    }

    public Water getWaterById(Long id) {

        return waterRepository.getWaterById(id);
    }

    public void addNewWater(Water water) {

        waterRepository.saveAndFlush(water);
    }
}
