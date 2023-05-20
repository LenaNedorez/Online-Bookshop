package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookReviewDto;
import com.example.MyBookShopApp.security.BookstoreUser;
import com.example.MyBookShopApp.security.BookstoreUserRegister;
import com.example.MyBookShopApp.services.BookReviewService;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class BookReviewController {

    private final BookReviewService bookReviewService;
    private final BookstoreUserRegister bookstoreUserRegister;
    private final BookService bookService;

    @Autowired
    public BookReviewController(BookReviewService bookReviewService, BookstoreUserRegister bookstoreUserRegister, BookService bookService) {
        this.bookReviewService = bookReviewService;
        this.bookstoreUserRegister = bookstoreUserRegister;
        this.bookService = bookService;
    }

    @PostMapping("/api/bookReview/{bookId}")
    public ResponseEntity<BookReviewDto> reviewBook(@PathVariable("bookId") Integer bookId, @RequestParam String text) {
        BookstoreUser bookstoreUser = (BookstoreUser) bookstoreUserRegister.getCurrentUser();
        return ResponseEntity.ok(bookReviewService.postBookReview(LocalDateTime.now(), bookService.getBookById(bookId), bookstoreUser, text));
    }

}
