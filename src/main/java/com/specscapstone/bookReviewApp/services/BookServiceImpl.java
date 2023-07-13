package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.BookDto;
import com.specscapstone.bookReviewApp.entities.Author;
import com.specscapstone.bookReviewApp.entities.Book;
import com.specscapstone.bookReviewApp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookDto::new).toList();
    }

    @Override
    public Optional<BookDto> getBookById(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        return bookOptional.map(BookDto::new);
    }

    @Override
    @Transactional
    public List<String> addBook(BookDto bookDto) {
        List<String> response = new ArrayList<>();

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        Author author = new Author();
        author.setAuthorId(bookDto.getAuthorId());
        book.setAuthor(author);
        book.setIsbn(bookDto.getIsbn());
        book.setImage(bookDto.getImage());
        bookRepository.saveAndFlush(book);
        response.add("Book added successfully");

        return response;
    }

    @Override
    @Transactional
    public List<String> updateBook(Long bookId, BookDto bookDto) {
        List<String> response = new ArrayList<>();

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(bookDto.getTitle());
            Author author = new Author();
            author.setAuthorId(bookDto.getAuthorId());
            book.setAuthor(author);
            book.setIsbn(bookDto.getIsbn());
            book.setImage(bookDto.getImage());
            bookRepository.saveAndFlush(book);
            response.add("Book updated successfully");
        } else {
            response.add("Book not found");
        }

        return response;
    }

    @Override
    @Transactional
    public List<String> deleteBook(Long bookId) {
        List<String> response = new ArrayList<>();

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(bookId);
            response.add("Book deleted successfully");
        } else {
            response.add("Book not found");
        }

        return response;
    }
}
