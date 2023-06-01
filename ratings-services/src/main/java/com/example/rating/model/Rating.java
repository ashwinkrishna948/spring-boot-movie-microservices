package com.example.rating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating 
{
    @Id
    @GeneratedValue

    private Long id;

    private String name;

    private double avgRating;

    private int count;
}
