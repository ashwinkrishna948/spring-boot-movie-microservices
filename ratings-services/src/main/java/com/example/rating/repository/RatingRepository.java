package com.example.rating.repository;

import com.example.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> 
{
   Rating findByName(String name);

   List<Rating> findAllByAvgRatingBetween(double min , double max);
}
