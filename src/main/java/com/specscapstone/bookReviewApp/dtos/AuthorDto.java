package com.specscapstone.bookReviewApp.dtos;

import com.specscapstone.bookReviewApp.entities.Author;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto implements Serializable {
    private Long authorId;
    private String name;

    public AuthorDto(Author author) {
        this.authorId = author.getAuthorId();
        this.name = author.getName();
    }
}
