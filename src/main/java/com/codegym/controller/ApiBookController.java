package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.Category;
import com.codegym.model.Publisher;
import com.codegym.model.Status;
import com.codegym.service.BookService;
import com.codegym.service.CategoryService;
import com.codegym.service.PublisherService;
import com.codegym.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class ApiBookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private PublisherService publisherService;

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

    @PostMapping("/api/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Status status = statusService.findById(book.getStatusId());
        Category category = categoryService.findById(book.getCategoryId());
        Publisher publisher = publisherService.findById(book.getPublisherId());

        book.setStatus(status);
        book.setCategory(category);
        book.setPublisher(publisher);
        bookService.save(book);

        return new ResponseEntity<>(book,HttpStatus.CREATED);
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
