package com.specscapstone.bookReviewApp.entities;

import jakarta.persistence.*;
import java.util.*;
import com.specscapstone.bookReviewApp.dtos.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
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
        if (authorDto.getName() != null) {
            this.name = authorDto.getName();
        }
    }

    public Author(Long authorId) {
        this.authorId = authorId;
    }
}
