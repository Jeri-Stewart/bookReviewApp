package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.RatingDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface RatingService {
    List<String> addRating(RatingDto ratingDto, Long bookId);

    @Transactional
    List<String> updateRating(Long ratingId, RatingDto ratingDto, String username);

    @Transactional
    List<String> deleteRating(Long ratingId, String username);

    Double getOverallRating(Long bookId);
}
