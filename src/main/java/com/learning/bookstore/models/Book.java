package com.learning.bookstore.models;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private Double price;
    private LocalDate publicationDate  = LocalDate.now();;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Default constructor
    public Book() {
    }

    // Constructor with parameters
    public Book(Long id, String title, String isbn, Double price, Author author, Category category) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.author = author;
        this.category = category;
    }
}
