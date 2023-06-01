package com.example.movie.api;

import com.example.movie.model.Movie;
import com.example.movie.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertFalse; 

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerIntTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MovieRepository movieRepository;

    @BeforeEach
    void cleanUp()
    {
        movieRepository.deleteAllInBatch();
    }

    @Test
    void testCreateMovie() throws Exception 
    {
        Movie movie = new Movie();
        movie.setName("Vikram");
        movie.setDirector("Lokesh Kanagraj");
        movie.setActors(List.of("KamalHassan", "VijaySethupati", "FahadhFaasil"));

        var response = mockMvc.perform(post("/movies")
                               .contentType(MediaType.APPLICATION_JSON)
                               .content(objectMapper.writeValueAsString(movie)));
 
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(notNullValue())))
                .andExpect(jsonPath("$.name",is(movie.getName())))
                .andExpect(jsonPath("$.director",is(movie.getDirector())))
                .andExpect(jsonPath("$.id",is(movie.getActors())));
    }

    @Test
    void testGetMovie() throws Exception
    {
        Movie movie = new Movie();
        movie.setName("Vikram");
        movie.setDirector("Lokesh Kanagraj");
        movie.setActors(List.of("KamalHassan", "VijaySethupati", "FahadhFaasil"));

        Movie savedMovie = movieRepository.save(movie);

        var response = mockMvc.perform(get("/movies/" + savedMovie.getId()));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(savedMovie.getId().intValue())))
                .andExpect(jsonPath("$.name",is(movie.getName())))
                .andExpect(jsonPath("$.director",is(movie.getDirector())))
                .andExpect(jsonPath("$.id",is(movie.getActors())));
    }

    @Test
    void testUpdateMovie() throws Exception
    {
        Movie movie = new Movie();
        movie.setName("Vikram");
        movie.setDirector("Lokesh Kanagraj");
        movie.setActors(List.of("KamalHaasan", "VijaySethupati", "FahadhFaasil"));

        Movie updatedMovie = movieRepository.save(movie);
        Long id = updatedMovie.getId();

        movie.setActors(List.of("KamalHaasan","VijaySethupati","FahadhFaasil","Suriya"));

        var response = mockMvc.perform(put("/movies/" + id)
                              .contentType(MediaType.APPLICATION_JSON)
                              .content(objectMapper.writeValueAsString(movie)));


        response.andDo(print())
                .andExpect(status().isOk());

        var fetchResponse = mockMvc.perform(get("/movies/" + id));

        fetchResponse.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(movie.getName())))
                .andExpect(jsonPath("$.director",is(movie.getDirector())))
                .andExpect(jsonPath("$.id",is(movie.getActors())));       
                
    }

    @Test
    void testDeleteMovie() throws Exception
    {
        Movie movie = new Movie();
        movie.setName("Vikram");
        movie.setDirector("Lokesh Kanagraj");
        movie.setActors(List.of("KamalHaasan", "VijaySethupati", "FahadhFaasil"));
       
       Movie savedMovie = movieRepository.save(movie);
       Long id = savedMovie.getId();
       
       mockMvc.perform(delete("/movies/" + id))
              .andDo(print())
              .andExpect(status().isOk());
       assertFalse(movieRepository.findById(id).isPresent());       

    }

}
