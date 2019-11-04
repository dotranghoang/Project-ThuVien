package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String book;
    private String author;
    private Long categoryId;
    private Long publisherId;
    private Long statusId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Book(String book, String author, Long categoryId, Long publisherId, Long statusId, Category category, Publisher publisher, Status status) {
        this.book = book;
        this.author = author;
        this.categoryId = categoryId;
        this.publisherId = publisherId;
        this.statusId = statusId;
        this.category = category;
        this.publisher = publisher;
        this.status = status;
    }

    public Book() {
    }

    public Book(String book, String author) {
        this.book = book;
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Book(String book, String author, Category category, Publisher publisher, Status status) {
        this.book = book;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.status = status;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Book(String book, String author, Category category, Publisher publisher) {
        this.book = book;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Book(String book, String author, Category category) {
        this.book = book;
        this.author = author;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
