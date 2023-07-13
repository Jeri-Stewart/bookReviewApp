package com.specscapstone.bookReviewApp.controllers;

import com.specscapstone.bookReviewApp.dtos.AuthorDto;
import com.specscapstone.bookReviewApp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{authorId}")
    public Optional<AuthorDto> getAuthorById(@PathVariable Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping
    public List<String> addAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.addAuthor(authorDto);
    }

    @PutMapping("/{authorId}")
    public List<String> updateAuthor(@PathVariable Long authorId, @RequestBody AuthorDto authorDto) {
        return authorService.updateAuthor(authorId, authorDto);
    }

    @DeleteMapping("/{authorId}")
    public List<String> deleteAuthor(@PathVariable Long authorId) {
        return authorService.deleteAuthor(authorId);
    }
}
