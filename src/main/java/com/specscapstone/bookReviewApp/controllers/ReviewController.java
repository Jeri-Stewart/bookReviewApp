package com.specscapstone.bookReviewApp.controllers;

import com.specscapstone.bookReviewApp.dtos.ReviewDto;
import com.specscapstone.bookReviewApp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    // Get all reviews by user ID
    @GetMapping("/user/{userId}")
    public List<ReviewDto> getReviewsByUserId(@PathVariable Long userId) {
        return reviewService.getAllReviewsByUserId(userId);
    }

    // Get review by ID
    @GetMapping("/{reviewId}")
    public Optional<ReviewDto> getReviewById(@PathVariable Long reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    // Add review for a book and user
    @PostMapping("/user/{userId}/book/{bookId}")
    public List<String> addReviewForUserAndBook(@RequestBody ReviewDto reviewDto, @PathVariable Long userId, @PathVariable Long bookId) {
        return reviewService.addReview(reviewDto, userId, bookId);
    }

    // Update review
    @PutMapping("/{reviewId}")
    public List<String> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto) {
        return reviewService.updateReview(reviewId, reviewDto);
    }

    // Delete review
    @DeleteMapping("/{reviewId}")
    public List<String> deleteReview(@PathVariable Long reviewId) {
        return reviewService.deleteReview(reviewId);
    }
}


