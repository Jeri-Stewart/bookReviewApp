package com.specscapstone.bookReviewApp.dtos;

import com.specscapstone.bookReviewApp.entities.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private BookDto book;
    private String review;
    private UserDto userDto;

    public ReviewDto(Review review) {
        this.reviewId = review.getReviewId();
        this.book = new BookDto(review.getBook());
        this.review = review.getReview();
    }

}



