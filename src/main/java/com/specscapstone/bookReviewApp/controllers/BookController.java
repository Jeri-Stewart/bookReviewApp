package com.specscapstone.bookReviewApp.controllers;

import com.specscapstone.bookReviewApp.dtos.BookDto;
import com.specscapstone.bookReviewApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public Optional<BookDto> getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public List<String> addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PutMapping("/{bookId}")
    public List<String> updateBook(@PathVariable Long bookId, @RequestBody BookDto bookDto) {
        return bookService.updateBook(bookId, bookDto);
    }

    @DeleteMapping("/{bookId}")
    public List<String> deleteBook(@PathVariable Long bookId) {
        return bookService.deleteBook(bookId);
    }
}

