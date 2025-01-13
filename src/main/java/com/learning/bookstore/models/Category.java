package com.learning.bookstore.models;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // Default constructor
    public Category() {
    }

    // Constructor with parameters
    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
