package com.specscapstone.bookReviewApp.dtos;

import com.specscapstone.bookReviewApp.entities.Rating;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto implements Serializable {
    private Long ratingId;
    private BookDto book;
    private AuthorDto author;
    private UserDto user;
    private Float rating;
    private Float overallRanking;

    public RatingDto(Rating rating) {
        this.ratingId = rating.getRatingId();
        this.book = new BookDto(rating.getBook());
        this.author = new AuthorDto(rating.getAuthor());
        this.user = new UserDto(rating.getUser());
        this.rating = rating.getRating();
        this.overallRanking = rating.getOverallRanking();
    }
}
