package com.codecool.servicewater.service;

import com.codecool.servicewater.model.RatingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class RatingServiceCaller {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-rating.url}")
    private String serviceUrl;


    public List<RatingResult> getRatingsByWaterId(Long waterId) {

        RatingResult[] ratingResults = restTemplate.getForEntity(serviceUrl +"/" + waterId, RatingResult[].class).getBody();
        return Arrays.asList(ratingResults);
    }
}
