package com.example.movie.services;

import com.example.movie.model.Movie;
import com.example.movie.repository.MovieRepository;
import com.example.movie.exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
 
    public Movie create (Movie movie)
    {
        if(movie==null)
          throw new InvalidDataException("Invalid Movie Request");
        
        return movieRepository.save(movie);   
    }

    public Movie read(Long id)
    {
        return movieRepository.findById(id)
                       .orElseThrow(()-> new NotFoundException("No movie of id"+id+" is available"));
    }

    public void update(Long id, Movie update)
    {
        if(update==null || id == null)
          throw new InvalidDataException("Invalid Movie Request");
          
        if(movieRepository.existsById(id))
        {
            Movie movie = movieRepository.getReferenceById(id);
            movie.setName(update.getName());
            movie.setActors(update.getActors());
            movie.setDirector(update.getDirector());
            movieRepository.save(movie);
        }
        
        else
        {
            throw new NotFoundException("Movie not found");
        }

    }

    public void delete(Long id)
    {
        if(movieRepository.existsById(id))
        {
            movieRepository.deleteById(id);
        }
        else
        {
            throw new NotFoundException("Movie not found");
        }
        
    }
    
}
