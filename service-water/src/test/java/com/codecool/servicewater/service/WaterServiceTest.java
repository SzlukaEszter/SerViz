package com.codecool.servicewater.service;

import com.codecool.servicewater.model.Water;
import com.codecool.servicewater.repository.WaterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class WaterServiceTest {

    @Autowired
    WaterRepository waterRepository;

    @Test
    public void saveWaterEntityAndGetTheSavedEntityFromDatabase() {

        Water water = Water.builder()
                .description("test")
                .name("test")
                .price(new BigDecimal(99))
                .rating(5)
                .picture("test")
                .build();

        waterRepository.save(water);
        Water foundWater = waterRepository.getWaterById(water.getId());

        assertNotNull(foundWater);
        assertEquals(water.getName(), foundWater.getName());
    }

}