package com.learning.bookstore.repositories;

import com.learning.bookstore.models.Author;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author(null, "Test Author", "Author bio");
    }

    @Test
    void testSaveAuthor() {
        Author savedAuthor = authorRepository.save(author);
        assertNotNull(savedAuthor);
        assertEquals("Test Author", savedAuthor.getName());
    }

    @Test
    void testFindAuthorById() {
        authorRepository.save(author);
        Author foundAuthor = authorRepository.findById(author.getId()).orElse(null);
        assertNotNull(foundAuthor);
        assertEquals("Test Author", foundAuthor.getName());
    }

    @Test
    void testDeleteAuthor() {
        authorRepository.save(author);
        authorRepository.deleteById(author.getId());
        assertFalse(authorRepository.findById(author.getId()).isPresent());
    }

    @Test
    void testFindAllAuthors() {
        authorRepository.save(author);
        Iterable<Author> authors = authorRepository.findAll();
        assertNotNull(authors);
        assertTrue(authors.iterator().hasNext());
    }
}
