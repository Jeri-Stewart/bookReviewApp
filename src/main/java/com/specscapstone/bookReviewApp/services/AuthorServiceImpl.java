package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.AuthorDto;
import com.specscapstone.bookReviewApp.entities.Author;
import com.specscapstone.bookReviewApp.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(author -> new AuthorDto(author)).toList();
    }

    @Override
    public Optional<AuthorDto> getAuthorById(Long authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        return authorOptional.map(author -> new AuthorDto(author));
    }

    @Override
    @Transactional
    public List<String> addAuthor(AuthorDto authorDto) {
        List<String> response = new ArrayList<>();

        Author author = new Author(authorDto);
        authorRepository.saveAndFlush(author);
        response.add("Author added successfully");

        return response;
    }

    @Override
    @Transactional
    public List<String> updateAuthor(Long authorId, AuthorDto authorDto) {
        List<String> response = new ArrayList<>();

        Optional<Author> authorOptional = authorRepository.findById(authorDto.getAuthorId());
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(authorDto.getName());
            authorRepository.saveAndFlush(author);
            response.add("Author updated successfully");
        } else {
            response.add("Author not found");
        }

        return response;
    }

    @Override
    @Transactional
    public List<String> deleteAuthor(Long authorId) {
        List<String> response = new ArrayList<>();

        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            authorRepository.deleteById(authorId);
            response.add("Author deleted successfully");
        } else {
            response.add("Author not found");
        }

        return response;
    }
}
