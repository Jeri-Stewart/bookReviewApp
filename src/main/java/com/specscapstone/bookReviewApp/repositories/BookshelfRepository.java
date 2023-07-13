package com.specscapstone.bookReviewApp.repositories;

import com.specscapstone.bookReviewApp.dtos.BookDto;
import com.specscapstone.bookReviewApp.entities.Bookshelf;
import com.specscapstone.bookReviewApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {
    Optional<Bookshelf> findByUserAndBook(User user, BookDto book);

    List<Bookshelf> findAllByUserAndReadStatusIn(User user, List<String> unread);

    List<Bookshelf> findAllByUserAndReadStatus(User user, String read);
}
