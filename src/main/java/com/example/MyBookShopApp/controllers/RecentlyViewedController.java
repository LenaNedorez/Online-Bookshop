package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.security.BookstoreUser;
import com.example.MyBookShopApp.security.BookstoreUserRegister;
import com.example.MyBookShopApp.services.RecentlyViewedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecentlyViewedController {

    private final RecentlyViewedService recentlyViewedService;
    private final BookstoreUserRegister bookstoreUserRegister;

    @Autowired
    public RecentlyViewedController(RecentlyViewedService recentlyViewedService, BookstoreUserRegister bookstoreUserRegister) {
        this.recentlyViewedService = recentlyViewedService;
        this.bookstoreUserRegister = bookstoreUserRegister;
    }

    @GetMapping("/books/recently-viewed")
    public String recentlyViewedPage(Model model) {
        BookstoreUser bookstoreUser = (BookstoreUser) bookstoreUserRegister.getCurrentUser();
//        System.out.println(bookstoreUserRegister.getCurrentUser());
        model.addAttribute("recentlyViewedBooks", recentlyViewedService.getRecentlyViewedBooks(bookstoreUser));
        return "/books/recently-viewed";
    }
}
