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
    private Long userId;
    private BookDto book;
    private Long authorId;
    private String review;
    private Integer rating;
    private Integer overallRanking;
    private UserDto userDto;
    private AuthorDto authorDto;

    public ReviewDto(Review review) {
        this.reviewId = review.getReviewId();
        this.userId = review.getUser().getUserId();
        this.book = new BookDto(review.getBook());
        this.authorId = review.getAuthor().getAuthorId();
        this.review = review.getReview();
        this.rating = review.getRating();
        this.overallRanking = review.getOverallRanking();
    }
}


