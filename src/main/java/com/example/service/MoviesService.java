package com.example.service;


import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.example.model.Movies;
import com.example.repository.MoviesRepository;



import java.util.List;
import java.util.Optional;
	 
	@Service
	public class MoviesService {
	 
	    private final MoviesRepository movieRepository;
	 
	    
	    public MoviesService(MoviesRepository movieRepository) {
	        this.movieRepository = movieRepository;
	    }
	 
	    public Movies saveMovie(Movies movie) {
	        return movieRepository.save(movie);
	    }
	 
	   
	    public List<Movies> getAllMovies() {
	        return movieRepository.findAll();
	    }
	    
	    public Movies updateMovie(ObjectId id, Movies updatedMovie) {
	        Movies existingMovie = movieRepository.findById(id).orElse(null);
	        if (existingMovie != null) {
	            // Update the fields you want to update
	            existingMovie.setName(updatedMovie.getName());
	            existingMovie.setActor(updatedMovie.getActor());
	            existingMovie.setImageType(existingMovie.getImageType());
	            existingMovie.setBase64String(updatedMovie.getBase64String());
	            // Save the updated movie
	            return movieRepository.save(existingMovie);
	        } else {
	            // Handle the case when the movie with the given id is not found
	            return null;
	        }
	    }
	    public boolean deleteMovie(ObjectId id) {
	        Optional<Movies> existingMovieOptional = movieRepository.findById(id);
	        if (existingMovieOptional.isPresent()) {
	            movieRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	    
	}


