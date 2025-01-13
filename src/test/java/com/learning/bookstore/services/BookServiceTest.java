package com.learning.bookstore.services;

import com.learning.bookstore.models.Book;
import com.learning.bookstore.models.Author;
import com.learning.bookstore.models.Category;
import com.learning.bookstore.models.BookRequest;
import com.learning.bookstore.repositories.BookRepository;
import com.learning.bookstore.repositories.AuthorRepository;
import com.learning.bookstore.repositories.CategoryRepository;
import com.learning.bookstore.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private BookService bookService;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBookById_Found() {
        System.out.println("\nRunning test: testGetBookById_Found");

        Author author = new Author(1L, "Author Name", "Author Biography");
        Category category = new Category(2L, "Fiction", "Fictional Books");
        Book book = new Book(1L, "Book Title", "ISBN123", 20.0, author, category);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        System.out.println("Mocked book repository to return a book");

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Book Title", result.getTitle());
        System.out.println("Verified that the book was found and the title is correct");

        verify(bookRepository, times(1)).findById(1L);
        System.out.println("Verified that the book repository was called once");
    }

    @Test
    public void testGetBookById_NotFound() {
        System.out.println("\nRunning test: testGetBookById_NotFound");

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        System.out.println("Mocked book repository to return empty");

        assertThrows(ResourceNotFoundException.class, () -> bookService.getBookById(1L));
        System.out.println("Verified that ResourceNotFoundException is thrown when book is not found");

        verify(bookRepository, times(1)).findById(1L);
        System.out.println("Verified that the book repository was called once");
    }

    @Test
    public void testCreateBook() {
        System.out.println("Running test: testCreateBook");

        // Create a mock author and category
        Author mockAuthor = new Author(1L, "John Doe", "john@example.com");
        Category mockCategory = new Category(1L, "Fiction", "Fictional stories");

        // Mock BookRequest object
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("New Book");
        bookRequest.setIsbn("ISBN456");
        bookRequest.setPrice(25.0);
        bookRequest.setAuthorId(mockAuthor.getId());
        bookRequest.setCategoryId(mockCategory.getId());

        // Expected Book object to be returned after saving
        Book expectedBook = new Book(null, "New Book", "ISBN456", 25.0, mockAuthor, mockCategory);

        // Mock repository and service calls
        when(authorRepository.findById(mockAuthor.getId())).thenReturn(Optional.of(mockAuthor));
        when(categoryRepository.findById(mockCategory.getId())).thenReturn(Optional.of(mockCategory));
        when(bookRepository.save(any(Book.class))).thenReturn(expectedBook);

        // Call the service method
        Book result = bookService.createBook(bookRequest);

        // Assertions
        assertNotNull(result);
        assertEquals("New Book", result.getTitle());
        assertEquals("ISBN456", result.getIsbn());
        assertEquals(25.0, result.getPrice());
        assertEquals(mockAuthor, result.getAuthor());
        assertEquals(mockCategory, result.getCategory());

        // Verifications
        verify(authorRepository, times(1)).findById(mockAuthor.getId());
        verify(categoryRepository, times(1)).findById(mockCategory.getId());
        verify(bookRepository, times(1)).save(any(Book.class));

        System.out.println("Verified that the book was created with the correct details, including author and category.");
    }

    @Test
    public void testCreateBookWithAuthorAndCategory() {
        System.out.println("\nRunning test: testCreateBookWithAuthorAndCategory");
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("New Book");
        bookRequest.setIsbn("ISBN123");
        bookRequest.setPrice(15.0);
        bookRequest.setAuthorId(1L);
        bookRequest.setCategoryId(2L);

        Author author = new Author(1L, "F. Scott Fitzgerald", "Biography example");
        Category category = new Category(2L, "Fiction", "Fictional Books");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> {
            Book savedBook = invocation.getArgument(0);
            savedBook.setId(1L);
            return savedBook;
        });

        Book result = bookService.createBook(bookRequest);

        assertNotNull(result);
        assertEquals("New Book", result.getTitle());
        assertEquals(author, result.getAuthor());
        assertEquals(category, result.getCategory());
        verify(authorRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findById(2L);
        verify(bookRepository, times(1)).save(any(Book.class));
        System.out.println("Finished test.");
    }

    @Test
    public void testDeleteBook_Success() {
        System.out.println("\nRunning test: testDeleteBook_Success");

        Author author = new Author(1L, "Author Name", "Author Biography");
        Category category = new Category(2L, "Fiction", "Fictional Books");
        Book book = new Book(1L, "Book to Delete", "ISBN789", 15.0, author, category);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        System.out.println("Mocked book repository to return the book");

        bookService.deleteBook(1L);
        System.out.println("Called deleteBook method");

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).deleteById(1L);
        System.out.println("Verified that the book repository was called once to find and once to delete");
    }

    @Test
    public void testDeleteBook_NotFound() {
        System.out.println("\nRunning test: testDeleteBook_NotFound");

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        System.out.println("Mocked book repository to return empty");

        assertThrows(ResourceNotFoundException.class, () -> bookService.deleteBook(1L));
        System.out.println("Verified that ResourceNotFoundException is thrown when book is not found");

        verify(bookRepository, times(1)).findById(1L);
        System.out.println("Verified that the book repository was called once");
    }
}
