package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookReview;
import com.example.MyBookShopApp.data.BookReviewDto;
import com.example.MyBookShopApp.data.BookReviewDtoMapping;
import com.example.MyBookShopApp.repositories.BookReviewRepository;
import com.example.MyBookShopApp.security.BookstoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookReviewService {

    private final BookReviewDtoMapping bookReviewDtoMapping;
    private final BookReviewRepository bookReviewRepository;

    @Autowired
    public BookReviewService(BookReviewDtoMapping bookReviewDtoMapping, BookReviewRepository bookReviewRepository) {
        this.bookReviewDtoMapping = bookReviewDtoMapping;
        this.bookReviewRepository = bookReviewRepository;
    }

    public BookReviewDto getBookReviewById(Integer bookReviewId) {
        return bookReviewDtoMapping.mapToBookReviewDto(bookReviewRepository.findById(bookReviewId).get());
    }

    public List<BookReviewDto> getAllReviews() {
        return bookReviewRepository.findAll()
                .stream()
                .map(BookReviewDtoMapping::mapToBookReviewDto)
                .collect(Collectors.toList());
    }

    public BookReviewDto postBookReview(LocalDateTime dateTime, Book book, BookstoreUser bookstoreUser, String text) {
        return bookReviewDtoMapping.mapToBookReviewDto(bookReviewRepository.save(new BookReview(dateTime, book, bookstoreUser, text)));
    }
}
