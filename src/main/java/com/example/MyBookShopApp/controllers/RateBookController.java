package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookRateDto;
import com.example.MyBookShopApp.security.BookstoreUser;
import com.example.MyBookShopApp.security.BookstoreUserRegister;
import com.example.MyBookShopApp.services.BookRateService;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RateBookController {

    private final BookRateService bookRateService;
    private final BookstoreUserRegister bookstoreUserRegister;
    private final BookService bookService;

    @Autowired
    public RateBookController(BookRateService bookRateService, BookstoreUserRegister bookstoreUserRegister, BookService bookService) {
        this.bookRateService = bookRateService;
        this.bookstoreUserRegister = bookstoreUserRegister;
        this.bookService = bookService;
    }

    @PostMapping("/api/rateBook/{bookId}")
    public ResponseEntity<BookRateDto> rateBook(@PathVariable("bookId") Integer bookId, @RequestParam Byte value) {
        BookstoreUser bookstoreUser = (BookstoreUser) bookstoreUserRegister.getCurrentUser();
        return ResponseEntity.ok(bookRateService.postBookRate(new BookRateDto(bookService.getBookById(bookId), bookstoreUser, value)));
    }
}
