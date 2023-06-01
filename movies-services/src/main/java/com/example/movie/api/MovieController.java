package com.example.movie.api;

import com.example.movie.model.Movie;
import com.example.movie.services.MovieService;
//import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) 
    {
        Movie movie = movieService.read(id);
        log.info("Returned movie with id {} ", id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping()
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) 
    {
        Movie createdMovie = movieService.create(movie);
        log.info("Created movie with id {} ", movie.getId());
        return ResponseEntity.ok(createdMovie);

    }

    @PutMapping("/{id}")
    public void updateMovie(@PathVariable Long id , @RequestBody(required = false) Movie movie) 
    {
        movieService.update(id,movie);
        log.info("Updated movie with id {} ", id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) 
    {
        movieService.delete(id);
        log.info("Deleted movie with id {} ", id);
    }
}
