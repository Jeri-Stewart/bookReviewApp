package com.specscapstone.bookReviewApp.entities;

import jakarta.persistence.*;
import com.specscapstone.bookReviewApp.dtos.RatingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", unique = true)
    private Long ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonManagedReference
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "overall_ranking")
    private Float overallRanking;

    public Rating(RatingDto ratingDto) {
        if (ratingDto.getRating() != null) {
            this.rating = ratingDto.getRating();
        }
        if (ratingDto.getOverallRanking() != null) {
            this.overallRanking = ratingDto.getOverallRanking();
        }
    }

    public Object getUsername() {
        return null;
    }

    public void setUsername(String username) {
    }
}
