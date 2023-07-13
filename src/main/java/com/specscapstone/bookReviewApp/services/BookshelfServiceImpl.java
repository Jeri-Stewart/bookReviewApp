package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.BookshelfDto;
import com.specscapstone.bookReviewApp.entities.Bookshelf;
import com.specscapstone.bookReviewApp.entities.User;
import com.specscapstone.bookReviewApp.repositories.BookRepository;
import com.specscapstone.bookReviewApp.repositories.BookshelfRepository;
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
public class BookshelfServiceImpl implements BookshelfService {
    @Autowired
    private BookshelfRepository bookshelfRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<String> addBookToShelf(BookshelfDto bookshelfDto, Long bookId) {
        List<String> response = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            Optional<Bookshelf> existingBook = bookshelfRepository.findByUserAndBook(userOptional.get(), bookshelfDto.getBook());
            if (existingBook.isPresent()) {
                response.add("Book already exists in the bookshelf");
            } else {
                Bookshelf bookshelf = new Bookshelf(bookshelfDto);
                bookshelf.setUser(userOptional.get());
                bookshelfRepository.saveAndFlush(bookshelf);
                response.add("Book added to the bookshelf successfully");
            }
        } else {
            response.add("User not found");
        }

        return response;
    }

    @Override
    @Transactional
    public List<String> updateReadStatus(Long bookshelfId, String readStatus) {
        List<String> response = new ArrayList<>();

        Optional<Bookshelf> bookshelfOptional = bookshelfRepository.findById(bookshelfId);
        if (bookshelfOptional.isPresent()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isPresent()) {
                Bookshelf bookshelf = bookshelfOptional.get();
                // Check if logged user
                if (!bookshelf.getUser().equals(userOptional.get())) {
                    response.add("You don't have permission to update this bookshelf entry");
                    return response;
                }
                bookshelf.setReadStatus(readStatus);
                bookshelfRepository.saveAndFlush(bookshelf);
                response.add("Read status updated successfully");
            } else {
                response.add("User not found");
            }
        } else {
            response.add("Read status not updated properly");
        }

        return response;
    }

    @Override
    @Transactional
    public List<String> deleteBookFromShelf(Long bookshelfId) {
        List<String> response = new ArrayList<>();

        Optional<Bookshelf> bookshelfOptional = bookshelfRepository.findById(bookshelfId);
        if (bookshelfOptional.isPresent()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isPresent()) {
                Bookshelf bookshelf = bookshelfOptional.get();

                // Check if logged user
                if (!bookshelf.getUser().equals(userOptional.get())) {
                    response.add("You don't have permission to delete this book from this shelf");
                    return response;
                }

                bookshelfRepository.deleteById(bookshelfId);
                response.add("Book deleted successfully");
            } else {
                response.add("User not found");
            }
        } else {
            response.add("Book not found");
        }

        return response;
    }

    @Override
    public List<BookshelfDto> getUnreadAndReadingBooksByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<Bookshelf> bookshelfList = bookshelfRepository.findAllByUserAndReadStatusIn(userOptional.get(),
                    List.of("Unread", "Reading"));
            return bookshelfList.stream().map(bookshelf -> new BookshelfDto(bookshelf)).toList();
        }
        return List.of();
    }

    @Override
    public List<BookshelfDto> getReadBooksByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<Bookshelf> bookshelfList = bookshelfRepository.findAllByUserAndReadStatus(userOptional.get(), "Read");
            return bookshelfList.stream().map(bookshelf -> new BookshelfDto(bookshelf)).toList();
        }
        return List.of();
    }
}

