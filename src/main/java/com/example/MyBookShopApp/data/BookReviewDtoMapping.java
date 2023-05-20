package com.example.MyBookShopApp.data;

import org.springframework.stereotype.Service;

@Service
public class BookReviewDtoMapping {

    public static BookReviewDto mapToBookReviewDto(BookReview bookReview){
        BookReviewDto bookReviewDto = new BookReviewDto();
        bookReviewDto.setId(bookReview.getId());
        bookReviewDto.setDateTime(bookReview.getDateTime());
        bookReviewDto.setBook(bookReview.getBook());
        bookReviewDto.setBookstoreUser(bookReview.getBookstoreUser());
        bookReviewDto.setText(bookReview.getText());
        return bookReviewDto;
    }

    public static BookReview mapToBookReview(BookReviewDto bookReviewDto){
        BookReview bookReview = new BookReview();
        bookReview.setId(bookReviewDto.getId());
        bookReview.setDateTime(bookReviewDto.getDateTime());
        bookReviewDto.setBook(bookReview.getBook());
        bookReview.setBookstoreUser(bookReviewDto.getBookstoreUser());
        bookReview.setText(bookReviewDto.getText());
        return bookReview;
    }

}
