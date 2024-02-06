package com.example.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.io.File;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
      // Check if the movie has a base64 encoded image
      if (movie.getBase64String() != null && !movie.getBase64String().isEmpty()) {
          // Decode base64 string to byte array
    	  System.out.println("wro");
          String imageString = (movie.getBase64String().split(","))[1];
          byte[] imageByte = Base64.getDecoder().decode(imageString);
          ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
          Path currentRelativePath = Paths.get("");
          String s = currentRelativePath.toAbsolutePath().toString();
          
          
          try {
              // Define the directory where you want to save the image
     
        	  String file_name = "image_" + movie.getName()+ "." + movie.getImageType();
        	  String path_to_dir = s + "/photos/";
        	  String path_to_assets = "C:\\angular lol\\my_app\\src\\assets\\";
        	  File directoryFile = new File(path_to_dir);
        	  if (!directoryFile.exists()) {
        		  directoryFile.mkdirs();
        	  }
        	  File outputFile2 = new File(path_to_assets, file_name);
        	  File outputFile = new File(path_to_dir, file_name);
        	  BufferedImage image=ImageIO.read(bis);


              ImageIO.write(image, movie.getImageType(), outputFile);
              ImageIO.write(image, movie.getImageType(), outputFile2);
              movie.setImagePath(outputFile.getAbsolutePath());
              movie.setImagePathAsset(outputFile2.getAbsolutePath());
              
        	  
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
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




