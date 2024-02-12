package com.example.service;


import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.example.model.Books;
import com.example.repository.BooksRepository;



import java.util.List;
import java.util.Optional;
	 
	@Service
	public class BooksService {
	 
	    private final BooksRepository bookRepository;
	 
	    
	    public BooksService(BooksRepository bookRepository) {
	        this.bookRepository = bookRepository;
	    }
	 
	    public Books saveBook(Books book) {
	        return bookRepository.save(book);
	    }
	 
	   
	    public List<Books> getAllBooks() {
	        return bookRepository.findAll();
	    }
	    
	    public Books updateBooks(String name, Books updatedBook) {
	    	try {
	        Books existingBook = bookRepository.findByNameContaining(name).get(0);
	        if (existingBook != null) {
	            // Update the fields you want to update
	            existingBook.setName(updatedBook.getName());
	            existingBook.setAuthor(updatedBook.getAuthor());
	            existingBook.setImageType(existingBook.getImageType());
	            existingBook.setBase64String(updatedBook.getBase64String());
	            // Save the updated books
	            return bookRepository.save(existingBook);
	        } else {
	            // Handle the case when the book with the given id is not found
	            return null;
	        }
	    	}
	    	catch (Exception e) {
	    		e.printStackTrace();
	    	}
			return updatedBook;
	    }
	    public boolean deleteBook(String name ) {
	        Optional<Books> existingBookOptional = Optional.ofNullable(bookRepository.findByNameContaining(name).get(0));
	        if (existingBookOptional.isPresent()) {
	            bookRepository.deleteByName(name);
	            return true;
	        }
	        return false;
	    }
	    
	}