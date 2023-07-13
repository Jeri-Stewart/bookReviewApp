package com.specscapstone.bookReviewApp.repositories;

import com.specscapstone.bookReviewApp.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
