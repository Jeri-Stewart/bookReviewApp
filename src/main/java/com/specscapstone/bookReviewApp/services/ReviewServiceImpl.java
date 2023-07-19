package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.AuthorDto;
import com.specscapstone.bookReviewApp.dtos.BookDto;
import com.specscapstone.bookReviewApp.dtos.ReviewDto;
import com.specscapstone.bookReviewApp.entities.Book;
import com.specscapstone.bookReviewApp.entities.Review;
import com.specscapstone.bookReviewApp.entities.User;
import com.specscapstone.bookReviewApp.repositories.AuthorRepository;
import com.specscapstone.bookReviewApp.repositories.BookRepository;
import com.specscapstone.bookReviewApp.repositories.ReviewRepository;
import com.specscapstone.bookReviewApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviewList = reviewRepository.findAllWithBookAndAuthor();
        return reviewList.stream()
                .map(review -> {
                    ReviewDto reviewDto = new ReviewDto(review);
                    BookDto bookDto = new BookDto(review.getBook());
                    bookDto.setAuthor(new AuthorDto(review.getBook().getAuthor()));
                    reviewDto.setBook(bookDto);
                    return reviewDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllReviewsByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<Review> reviewList = reviewRepository.findAllByUser(userOptional.get());
            return reviewList.stream().map(ReviewDto::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<ReviewDto> getReviewById(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        return reviewOptional.map(ReviewDto::new);
    }

    @Override
    @Transactional
    public List<String> addReviewByBookId(ReviewDto reviewDto, Long bookId) {
        List<String> response = new ArrayList<>();

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isPresent()) {
                Review review = new Review(reviewDto);
                review.setBook(bookOptional.get());
                review.setUser(userOptional.get());
                reviewRepository.saveAndFlush(review);
                response.add("Review added successfully");
            } else {
                response.add("User not found");
            }
        } else {
            response.add("Book not found");
        }

        return response;
    }

    @Override
    @Transactional
    public List<String> addReview(ReviewDto reviewDto, Long userId, Long bookId) {
        List<String> response = new ArrayList<>();

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            Review review = new Review(reviewDto);
            review.setUser(userOptional.get());
            review.setBook(bookOptional.get());
            reviewRepository.saveAndFlush(review);
            response.add("Review added successfully");
        } else {
            response.add("User or Book not found");
        }

        return response;
    }

    @Override
    @Transactional
    public List<String> updateReview(Long reviewId, ReviewDto reviewDto) {
        List<String> response = new ArrayList<>();

        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
                Review review = reviewOptional.get();
                review.setReview(reviewDto.getReview());
                reviewRepository.saveAndFlush(review);
                response.add("Review updated successfully");
            } else {
            response.add("Review not found");
        }

        return response;
    }

    @Override
    @Transactional
    public List<String> deleteReview(Long reviewId) {
        List<String> response = new ArrayList<>();

        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
                reviewRepository.deleteById(reviewId);
                response.add("Review deleted successfully");
            } else {
            response.add("Review not found");
        }

        return response;
    }
}





