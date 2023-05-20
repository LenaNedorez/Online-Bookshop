package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewRepository extends JpaRepository<BookReview, Integer> {

    BookReview save(BookReview bookReview);
}
