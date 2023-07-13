package com.specscapstone.bookReviewApp.dtos;

import com.specscapstone.bookReviewApp.entities.Review;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto implements Serializable {
    private Long reviewId;
    private BookDto book;
    private AuthorDto author;
    private String review;

    public ReviewDto(Review review) {
        this.reviewId = review.getReviewId();
        this.book = new BookDto(review.getBook());
        this.author = new AuthorDto(review.getAuthor());
        this.review = review.getReview();
    }
}
