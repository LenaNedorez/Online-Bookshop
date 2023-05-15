package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenresPageController {

    private final BookService bookService;

    @Autowired
    public GenresPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/genres")
    public String genresPage(){
        return "/genres/index";
    }
}
