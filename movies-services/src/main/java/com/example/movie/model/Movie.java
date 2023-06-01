package com.example.movie.model;

import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Id;

import lombok.Data;

import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;

@Entity
@Data
public class Movie {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;

    private String director;
    
    @ElementCollection
    private List<String> actors = new ArrayList<>();
}
