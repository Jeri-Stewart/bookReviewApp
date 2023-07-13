package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.RatingDto;
import com.specscapstone.bookReviewApp.entities.Book;
import com.specscapstone.bookReviewApp.entities.Rating;
import com.specscapstone.bookReviewApp.repositories.BookRepository;
import com.specscapstone.bookReviewApp.repositories.RatingRepository;
import com.specscapstone.bookReviewApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<String> addRating(RatingDto ratingDto, Long bookId) {
        List<String> response = new ArrayList<>();

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Rating rating = new Rating(ratingDto);
            rating.setBook(bookOptional.get());
            rating.setUsername(username);
            ratingRepository.saveAndFlush(rating);

            updateOverallRating(bookId);

            response.add("Rating added successfully");
        } else {
            response.add("Book not found");
        }

        return response;
    }

    @Override
    @Transactional
    public List<String> updateRating(Long ratingId, RatingDto ratingDto, String username) {
        List<String> response = new ArrayList<>();

        Optional<Rating> ratingOptional = ratingRepository.findById(ratingId);
        if (ratingOptional.isPresent()) {
            Rating rating = ratingOptional.get();

            if (!rating.getUsername().equals(username)) {
                response.add("You don't have permission to update this rating");
                return response;
            }

            rating.setRating(ratingDto.getRating());
            ratingRepository.saveAndFlush(rating);

            updateOverallRating(rating.getBook().getBookId());

            response.add("Rating updated successfully");
        } else {
            response.add("Rating not found");
        }

        return response;
    }

    @Override
    @Transactional
    public List<String> deleteRating(Long ratingId, String username) {
        List<String> response = new ArrayList<>();

        Optional<Rating> ratingOptional = ratingRepository.findById(ratingId);
        if (ratingOptional.isPresent()) {
            Rating rating = ratingOptional.get();

            if (!rating.getUsername().equals(username)) {
                response.add("You don't have permission to delete this rating");
                return response;
            }

            ratingRepository.deleteById(ratingId);

            updateOverallRating(rating.getBook().getBookId());

            response.add("Rating deleted successfully");
        } else {
            response.add("Rating not found");
        }

        return response;
    }

    @Override
    public Double getOverallRating(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            List<Rating> ratings = ratingRepository.findAllByBook(bookOptional.get());
            if (!ratings.isEmpty()) {
                double sum = ratings.stream().mapToDouble(Rating::getRating).sum();
                return sum / ratings.size();
            }
        }
        return null;
    }

    private void updateOverallRating(Long bookId) {
        Double overallRating = getOverallRating(bookId);
        if (overallRating != null) {
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            bookOptional.ifPresent(book -> {
                book.setOverallRating(overallRating);
                bookRepository.saveAndFlush(book);
            });
        }
    }
}
