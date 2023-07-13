package com.specscapstone.bookReviewApp.repositories;

import com.specscapstone.bookReviewApp.entities.Book;
import com.specscapstone.bookReviewApp.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllByBook(Book book);
}
