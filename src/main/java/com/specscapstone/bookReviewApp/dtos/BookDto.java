package com.specscapstone.bookReviewApp.dtos;

import com.specscapstone.bookReviewApp.entities.Author;
import com.specscapstone.bookReviewApp.entities.Book;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable {
    private Long bookId;
    private String title;
    private Long authorId;
    private String isbn;
    private String image;

    public BookDto(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.authorId = book.getAuthor().getAuthorId();
        this.isbn = book.getIsbn();
        this.image = book.getImage();
    }
}

