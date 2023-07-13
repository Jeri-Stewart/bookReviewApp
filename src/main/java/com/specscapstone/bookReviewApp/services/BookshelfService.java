package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.BookshelfDto;

import java.util.List;

public interface BookshelfService {
    List<String> addBookToShelf(BookshelfDto bookshelfDto, Long bookId);

    List<String> updateReadStatus(Long bookshelfId, String readStatus);

    List<String> deleteBookFromShelf(Long bookshelfId);

    List<BookshelfDto> getUnreadAndReadingBooksByUserId(Long userId);

    List<BookshelfDto> getReadBooksByUserId(Long userId);
}
