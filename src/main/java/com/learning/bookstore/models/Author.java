package com.learning.bookstore.models;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String biography;

    // Default constructor
    public Author() {
    }

    // Constructor with parameters
    public Author(Long id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
    }

}
