package com.learning.bookstore.services;

import com.learning.bookstore.models.Author;
import com.learning.bookstore.repositories.AuthorRepository;
import com.learning.bookstore.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    public AuthorServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAuthorById_Found() {
        System.out.println("\nRunning test: testGetAuthorById_Found");

        Author author = new Author(1L, "Author Name", "Author Biography");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        System.out.println("Mocked author repository to return an author");

        Author result = authorService.getAuthorById(1L);

        assertNotNull(result);
        assertEquals("Author Name", result.getName());
        System.out.println("Verified that the author was found and the name is correct");

        verify(authorRepository, times(1)).findById(1L);
        System.out.println("Verified that the author repository was called once");
    }

    @Test
    public void testGetAuthorById_NotFound() {
        System.out.println("\nRunning test: testGetAuthorById_NotFound");

        when(authorRepository.findById(1L)).thenReturn(Optional.empty());
        System.out.println("Mocked author repository to return empty");

        assertThrows(ResourceNotFoundException.class, () -> authorService.getAuthorById(1L));
        System.out.println("Verified that ResourceNotFoundException is thrown when author is not found");

        verify(authorRepository, times(1)).findById(1L);
        System.out.println("Verified that the author repository was called once");
    }

    @Test
    public void testCreateAuthor() {
        System.out.println("\nRunning test: testCreateAuthor");

        Author author = new Author(null, "New Author", "Author Biography");
        when(authorRepository.save(author)).thenReturn(author);
        System.out.println("Mocked author repository to save the new author");

        Author result = authorService.createAuthor(author);

        assertNotNull(result);
        assertEquals("New Author", result.getName());
        System.out.println("Verified that the author was created and the name is correct");

        verify(authorRepository, times(1)).save(author);
        System.out.println("Verified that the author repository was called once");
    }

    @Test
    public void testDeleteAuthor_Success() {
        System.out.println("\nRunning test: testDeleteAuthor_Success");

        Author author = new Author(1L, "Author to Delete", "Biography");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        System.out.println("Mocked author repository to return the author");

        authorService.deleteAuthor(1L);
        System.out.println("Called deleteAuthor method");

        verify(authorRepository, times(1)).findById(1L);
        verify(authorRepository, times(1)).deleteById(1L);
        System.out.println("Verified that the author repository was called once to find and once to delete");
    }

    @Test
    public void testDeleteAuthor_NotFound() {
        System.out.println("\nRunning test: testDeleteAuthor_NotFound");

        when(authorRepository.findById(1L)).thenReturn(Optional.empty());
        System.out.println("Mocked author repository to return empty");

        assertThrows(ResourceNotFoundException.class, () -> authorService.deleteAuthor(1L));
        System.out.println("Verified that ResourceNotFoundException is thrown when author is not found");

        verify(authorRepository, times(1)).findById(1L);
        System.out.println("Verified that the author repository was called once");
    }
}
