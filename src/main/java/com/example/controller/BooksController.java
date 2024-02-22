package com.example.controller;

import java.util.List;

//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.model.Books;
import com.example.service.BooksService;
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/books")
public class BooksController {

  private final BooksService bookService;

  public BooksController(BooksService bookService) {
      this.bookService = bookService;
  }

  @GetMapping()
  public List<Books> getBooks() {
      return bookService.getAllBooks();
  }

  @PostMapping()
  public Books addBooks(@RequestBody Books book) {
      return bookService.saveBook(book);
  }
  @CrossOrigin
  @PutMapping("/{name}")
  public Books updateBooks(@PathVariable("name") String name, @RequestBody Books updatedBook) {
      return bookService.updateBooks(name, updatedBook);
  }
  @CrossOrigin
  @DeleteMapping("/{name}")
  public ResponseEntity<String> deleteBook(@PathVariable("name") String name) {
      boolean deleted = bookService.deleteBook(name);
      if (deleted) {
          return ResponseEntity.ok("Movie deleted successfully");
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
  }
  
  @GetMapping("/{name}")
  public Books  getSingleBook(@PathVariable("name") String name) {
	return bookService.getSingleBook(name);
	  
  }

}


