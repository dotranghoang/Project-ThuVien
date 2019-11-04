package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.Category;
import com.codegym.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiBookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/book")
    public ResponseEntity<List<Book>> getBookList(){
        List<Book> books = (List<Book>) bookService.findAll();

        if(books.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("/api/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        Book book = bookService.findById(id);

        if(book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @DeleteMapping("/api/book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        Book book = bookService.findById(id);

        if(book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
