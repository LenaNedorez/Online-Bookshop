package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TagsPageController {

    private final BookService bookService;

    @Autowired
    public TagsPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/tags")
    public String tagsPage(){
        return "/tags/index";
    }
}
