package com.specscapstone.bookReviewApp.entities;

import jakarta.persistence.*;
import com.specscapstone.bookReviewApp.dtos.BookshelfDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "bookshelf")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelf_id", unique = true)
    private Long shelfId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonManagedReference
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @Column(name = "read_status")
    private String readStatus;

    public Bookshelf(BookshelfDto bookshelfDto) {
        if (bookshelfDto.getReadStatus() != null) {
            this.readStatus = bookshelfDto.getReadStatus();
        }
    }
}
