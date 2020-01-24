package com.codecool.servicerating.repository;

import com.codecool.servicerating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating findAllByWaterId(Long waterId);

    List<Rating> findAllByUserId(Long userId);

    /*
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Rating r SET r.comment = :comment WHERE r.id = :ratingId")
    int updateComment(@Param("ratingId") Long ratingId, @Param("comment") String comment);

     */
}
