package com.specscapstone.bookReviewApp.dtos;

import com.specscapstone.bookReviewApp.entities.Bookshelf;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookshelfDto implements Serializable {
    private Long shelfId;
    private BookDto book;
    private UserDto user;
    private String readStatus;

    public BookshelfDto(Bookshelf bookshelf) {
        this.shelfId = bookshelf.getShelfId();
        this.book = new BookDto(bookshelf.getBook());
        this.user = new UserDto(bookshelf.getUser());
        this.readStatus = bookshelf.getReadStatus();
    }
}
