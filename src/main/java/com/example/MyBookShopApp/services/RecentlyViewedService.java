package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookBrowsing;
import com.example.MyBookShopApp.repositories.BookBrowsingRepository;
import com.example.MyBookShopApp.security.BookstoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecentlyViewedService {

    private final BookBrowsingRepository bookBrowsingRepository;

    @Autowired
    public RecentlyViewedService(BookBrowsingRepository bookBrowsingRepository) {
        this.bookBrowsingRepository = bookBrowsingRepository;
    }

    public List<Book> getRecentlyViewedBooks(BookstoreUser bookstoreUser) {
        Sort.TypedSort<BookBrowsing> bookBrowsingSort = Sort.sort(BookBrowsing.class);
        Sort sort = bookBrowsingSort.by(BookBrowsing::getId).descending();
        return bookBrowsingRepository.findFirst10ByBookstoreUser(bookstoreUser, sort).stream().map(BookBrowsing::getBook).collect(Collectors.toList());
    }
}
