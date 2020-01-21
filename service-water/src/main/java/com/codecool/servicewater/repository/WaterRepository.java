package com.codecool.servicewater.repository;

import com.codecool.servicewater.model.Water;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface WaterRepository extends JpaRepository<Water, Long> {

    Water getWaterById(Long id);
}
