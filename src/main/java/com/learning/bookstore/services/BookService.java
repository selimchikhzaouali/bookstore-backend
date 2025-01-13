package com.learning.bookstore.services;

import com.learning.bookstore.models.Book;
import com.learning.bookstore.models.BookRequest;
import com.learning.bookstore.models.Author;
import com.learning.bookstore.models.Category;
import com.learning.bookstore.repositories.BookRepository;
import com.learning.bookstore.repositories.AuthorRepository;
import com.learning.bookstore.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.learning.bookstore.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    // Constructor injection for the repository
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by its ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    // Create a new book
    public Book createBook(BookRequest bookRequest) {
        // Fetch the author
        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + bookRequest.getAuthorId()));

        // Fetch the category
        Category category = categoryRepository.findById(bookRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + bookRequest.getCategoryId()));

        // Create the book
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());
        book.setPrice(bookRequest.getPrice());
        book.setAuthor(author);
        book.setCategory(category);

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (!existingBook.isPresent()) {
            throw new ResourceNotFoundException("Book with id " + id + " not found");
        }

        Book book = existingBook.get();
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        book.setPrice(updatedBook.getPrice());
        
        return bookRepository.save(book);
    }

    // Delete a book by its ID
    public void deleteBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (!bookOptional.isPresent()) {
            throw new ResourceNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}
