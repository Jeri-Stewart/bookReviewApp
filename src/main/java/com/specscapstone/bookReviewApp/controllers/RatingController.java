package com.specscapstone.bookReviewApp.controllers;

import com.specscapstone.bookReviewApp.dtos.RatingDto;
import com.specscapstone.bookReviewApp.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    // Add rating for a book
    @PostMapping("/book/{bookId}")
    public List<String> addRatingForBook(@RequestBody RatingDto ratingDto, @PathVariable Long bookId) {
        return ratingService.addRating(ratingDto, bookId);
    }

    // Update rating
    @PutMapping("/{ratingId}")
    public List<String> updateRating(@PathVariable Long ratingId, @RequestBody RatingDto ratingDto, Authentication authentication) {
        String username = authentication.getName();
        return ratingService.updateRating(ratingId, ratingDto, username);
    }

    // Delete rating
    @DeleteMapping("/{ratingId}")
    public List<String> deleteRating(@PathVariable Long ratingId, Authentication authentication) {
        String username = authentication.getName();
        return ratingService.deleteRating(ratingId, username);
    }
}
