package com.specscapstone.bookReviewApp.controllers;

import com.specscapstone.bookReviewApp.dtos.BookshelfDto;
import com.specscapstone.bookReviewApp.services.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookshelf")
public class BookshelfController {
    @Autowired
    private BookshelfService bookshelfService;

    @PostMapping("/book/{bookId}")
    public List<String> addBookToShelf(@RequestBody BookshelfDto bookshelfDto, @PathVariable Long bookId) {
        return bookshelfService.addBookToShelf(bookshelfDto, bookId);
    }

    @PutMapping("/{bookshelfId}")
    public List<String> updateReadStatus(@PathVariable Long bookshelfId, @RequestBody String readStatus) {
        return bookshelfService.updateReadStatus(bookshelfId, readStatus);
    }

    @DeleteMapping("/{bookshelfId}")
    public List<String> deleteBookFromShelf(@PathVariable Long bookshelfId) {
        return bookshelfService.deleteBookFromShelf(bookshelfId);
    }

    @GetMapping("/user/{userId}/bookshelf")
    public List<BookshelfDto> getUnreadAndReadingBooksByUserId(@PathVariable Long userId) {
        return bookshelfService.getUnreadAndReadingBooksByUserId(userId);
    }

    @GetMapping("/user/{userId}/read")
    public List<BookshelfDto> getReadBooksByUserId(@PathVariable Long userId) {
        return bookshelfService.getReadBooksByUserId(userId);
    }
}


