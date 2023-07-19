package com.specscapstone.bookReviewApp.entities;

import com.specscapstone.bookReviewApp.dtos.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", unique = true)
    private Long authorId;

    @Column(name = "name")
    private String name;

    // table relationships

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private Set<Book> books;

    public Author(AuthorDto authorDto) {
        if (authorDto != null) {
            this.authorId = authorDto.getAuthorId();
            this.name = authorDto.getName();
        }
    }
}

