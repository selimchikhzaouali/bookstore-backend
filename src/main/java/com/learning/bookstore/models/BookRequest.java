package com.learning.bookstore.models;

public class BookRequest {
    private String title;
    private String isbn;
    private Double price;
    private Long authorId;
    private Long categoryId;

    // Constructor
    public BookRequest() {}

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    // Optional toString method for logging or debugging
    @Override
    public String toString() {
        return "BookRequest{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", authorId=" + authorId +
                ", categoryId=" + categoryId +
                '}';
    }
}
