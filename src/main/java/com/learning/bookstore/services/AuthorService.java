package com.learning.bookstore.services;

import com.learning.bookstore.models.Author;
import com.learning.bookstore.repositories.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.learning.bookstore.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
    return authorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
}

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (!existingAuthor.isPresent()) {
            throw new ResourceNotFoundException("Author with id " + id + " not found");
        }

        Author author = existingAuthor.get();
        author.setName(updatedAuthor.getName());
        author.setBiography(updatedAuthor.getBiography());
        
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (!authorOptional.isPresent()) {
            throw new ResourceNotFoundException("Author not found");
        }
        authorRepository.deleteById(id);
    }
}
