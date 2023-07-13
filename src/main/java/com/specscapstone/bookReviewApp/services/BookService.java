package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.BookDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();
    Optional<BookDto> getBookById(Long bookId);
    @Transactional
    List<String> addBook(BookDto bookDto);
    @Transactional
    List<String> updateBook(Long bookId, BookDto bookDto);
    @Transactional
    List<String> deleteBook(Long bookId);
}

