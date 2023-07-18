package com.specscapstone.bookReviewApp.repositories;

import com.specscapstone.bookReviewApp.entities.Review;
import com.specscapstone.bookReviewApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN FETCH r.book b JOIN FETCH b.author")
    List<Review> findAllWithBookAndAuthor();

    List<Review> findAllByUser(User user);
}
