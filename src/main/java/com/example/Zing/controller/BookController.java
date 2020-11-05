package com.example.Zing.controller;

import com.example.Zing.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//sample controller skeleton
@RestController
@RequestMapping("/api/")
public class BookController {
//    @Autowired
//    private BookRepository bookRepository;
//
//    //get books
//    @GetMapping("books")
//    public List<Book> getAllBook() {
//        return this.bookRepository.findAll();
//    }
//
//    //get book by id
//
//    @GetMapping("/books/{id}")
//    public ResponseEntity<Book> getBookById(@PathVariable(value="id") Long bookId) throws ResourceNotFoundException {
//        Book book = bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Not found"));
//        return ResponseEntity.ok().body(book);
//    }
//
//    //save book
//    @PostMapping("books")
//    public Book createBook(@RequestBody Book book) throws ResourceNotFoundException {
//        Book newBook = this.bookRepository.save(book);
//        return newBook;
//    }
//
//    //update book
//    @PutMapping("books/{id}")
//    public ResponseEntity<Book> updateBook(@PathVariable(value="id") Long bookId, @Valid @RequestBody Book bookDetails) throws ResourceNotFoundException {
//        Book book = bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Not found"));
//        book.setAuthor(bookDetails.getAuthor());
//        book.setName(bookDetails.getName());
//
//        return ResponseEntity.ok(this.bookRepository.save(book));
//    }
//    //delete book
//    @DeleteMapping("books/{id}")
//    public Map<String, Boolean> deleteBook(@PathVariable(value="id") Long bookid) throws ResourceNotFoundException {
//        Book book = bookRepository.findById(bookid).orElseThrow(()->new ResourceNotFoundException("Not found"));
//        this.bookRepository.delete(book);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
}
