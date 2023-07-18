package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.ReviewDto;
import com.specscapstone.bookReviewApp.entities.Review;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDto> getAllReviews();
    List<ReviewDto> getAllReviewsByUserId(Long userId);
    Optional<ReviewDto> getReviewById(Long reviewId);
    List<String> addReview(ReviewDto reviewDto, Long bookId);
    List<String> addReview(ReviewDto reviewDto, Long userId, Long bookId);

    @Transactional
    List<String> updateReview(Long reviewId, ReviewDto reviewDto, String username);

    @Transactional
    List<String> deleteReview(Long reviewId, String username);
}



