package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.AuthorDto;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();
    Optional<AuthorDto> getAuthorById(Long authorId);
    List<String> addAuthor(AuthorDto authorDto);
    List<String> updateAuthor(Long authorId, AuthorDto authorDto);
    List<String> deleteAuthor(Long authorId);
}
