package com.codecool.servicecart.service;


import com.codecool.servicecart.model.WaterData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WaterCaller {

    @Autowired
    RestTemplate restTemplate;

    public WaterData getWater(Long waterId) {
        WaterData waterData = restTemplate.getForObject("http://service-water/water/water/" + waterId, WaterData.class);
        return waterData;
    }
}
