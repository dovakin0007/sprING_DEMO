package com.example.controller;

//import java.util.ArrayList;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.model.Movies;
import com.example.service.MoviesService;
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/movies")
public class MoviesController {

  private final MoviesService movieService;

  @Autowired
  public MoviesController(MoviesService movieService) {
      this.movieService = movieService;
  }

  @GetMapping()
  public List<Movies> getMovies() {
      return movieService.getAllMovies();
  }

  @PostMapping()
  public Movies addMovies(@RequestBody Movies movie) {
      return movieService.saveMovie(movie);
  }
  
  
  
  @PutMapping("/{id}")
  public Movies updateMovies(@PathVariable("id") ObjectId id, @RequestBody Movies updatedMovie) {
      return movieService.updateMovie(id, updatedMovie);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteMovie(@PathVariable("id") ObjectId id) {
      boolean deleted = movieService.deleteMovie(id);
      if (deleted) {
          return ResponseEntity.ok("Movie deleted successfully");
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
  }
 
}
 



