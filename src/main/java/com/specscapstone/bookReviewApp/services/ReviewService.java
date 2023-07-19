package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.ReviewDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDto> getAllReviews();

    List<ReviewDto> getAllReviewsByUserId(Long userId);
    Optional<ReviewDto> getReviewById(Long reviewId);

    @Transactional
    List<String> addReviewByBookId(ReviewDto reviewDto, Long bookId);

    List<String> addReview(ReviewDto reviewDto, Long userId, Long bookId);

    @Transactional
    List<String> updateReview(Long reviewId, ReviewDto reviewDto);
    @Transactional
    List<String> deleteReview(Long reviewId);
}



