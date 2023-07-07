package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecentController {

    private final BookService bookService;

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks() { return new ArrayList<>(); }

    @Autowired
    public RecentController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/recent")
    public String recentPage(Model model) {
        model.addAttribute("recentBooks", bookService.getRecentBooks());
        return "/books/recent";
    }
}
