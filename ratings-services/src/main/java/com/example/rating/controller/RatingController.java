package com.example.rating.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rating.model.Rating;
import com.example.rating.model.RatingRequest;
import com.example.rating.services.RatingService;



@RestController
@Slf4j
@RequestMapping("/ratings")
public class RatingController 
{
    @Autowired
    RatingService ratingService;
    
    
    @GetMapping("/{name}")
    public ResponseEntity<Rating> getRating(@PathVariable String name) 
    {
        Rating rating = ratingService.fetchRating(name);
        log.info(" Returning rating for movie: {} ", name);
        return ResponseEntity.ok(rating);
    }

    @PostMapping()
    public ResponseEntity<Rating> updateRating(@RequestBody RatingRequest request) 
    {
        Rating rating = ratingService.updateAverage(request.getName(), request.getStars());
        log.info("Returning new average for movie: {}",request.getName());
        return ResponseEntity.ok(rating);
    }
}
