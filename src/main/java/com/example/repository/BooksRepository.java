package com.example.repository;

import com.example.model.Books;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BooksRepository extends MongoRepository<Books, ObjectId> {

	Optional<Books> findById(String id);

	void deleteById(String id);
	
	List<Books> findByNameContaining(String name);
	
	 void deleteByName(String name);

	    // Update by name
	   @Query("{'name': ?0}")
	 void updateByName(String name, Books update);
	
}
