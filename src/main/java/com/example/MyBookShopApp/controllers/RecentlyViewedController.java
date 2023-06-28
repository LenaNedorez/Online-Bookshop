package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.security.BookstoreUser;
import com.example.MyBookShopApp.security.BookstoreUserRegister;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecentlyViewedController {

    private final BookService bookService;
    private final BookstoreUserRegister bookstoreUserRegister;

    @Autowired
    public RecentlyViewedController(BookService bookService, BookstoreUserRegister bookstoreUserRegister) {
        this.bookService = bookService;
        this.bookstoreUserRegister = bookstoreUserRegister;
    }

    @GetMapping("/books/recently-viewed")
    public String recentlyViewedPage(Model model) {
        BookstoreUser bookstoreUser = (BookstoreUser) bookstoreUserRegister.getCurrentUser();
        model.addAttribute("recentlyViewedBooks", bookService.getRecentlyViewedBooks(bookstoreUser));
        return "/books/recently-viewed";
    }
}
