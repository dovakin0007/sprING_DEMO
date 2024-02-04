package com.example.repository;
	
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.Movies;
	 
	public interface MoviesRepository extends MongoRepository<Movies,ObjectId> {
	    // Additional custom queries if needed
		void deleteById(ObjectId id);
	}


