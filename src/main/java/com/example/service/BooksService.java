package com.example.service;

import org.springframework.stereotype.Service;

import com.example.model.Books;
import com.example.repository.BooksRepository;
import java.util.List;
	 
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
	    
	    public Books getSingleBook(String name) {
	    	Books existingBookOptional = null;
	        try {
		         existingBookOptional = bookRepository.findByNameContaining(name);
		       
	        }catch (Exception e){
	        	e.printStackTrace();
	        }
	        return existingBookOptional;
	        
	    	
	    }
	    
	    public Books updateBooks(String name, Books updatedBook) {
	    	try {
	    	Books existingBook = bookRepository.findByNameContaining(name);
	        if (existingBook != null) {
	            // Update the fields you want to update
	        	if ( updatedBook.getName().isEmpty() == false|| updatedBook.getName()!= null) {
	        		existingBook.setName(updatedBook.getName());
	        	}if (updatedBook.getAuthor().isEmpty() == false || updatedBook.getAuthor()!= null) {
	        		existingBook.setAuthor(updatedBook.getAuthor());
	        	}if (updatedBook.getBase64String().isEmpty() == false || updatedBook.getBase64String()!= null) {
	        		existingBook.setBase64String(updatedBook.getBase64String());
	        	}if ( updatedBook.getImageType().isEmpty() == false || updatedBook.getImageType()!= null) {
	        		existingBook.setImageType(updatedBook.getImageType());
	        	}
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
	        Books existingBookOptional = bookRepository.findByNameContaining(name);
	        if (existingBookOptional != null) {
	            bookRepository.deleteByName(name);
	            return true;
	        }
	        return false;
	    }
	    
	}