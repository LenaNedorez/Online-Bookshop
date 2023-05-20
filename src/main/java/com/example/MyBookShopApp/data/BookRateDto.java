package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.security.BookstoreUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRateDto {

    private Integer id;

    private Book book;

    private BookstoreUser bookstoreUser;

    private Byte rate;

    public BookRateDto(Book book, BookstoreUser bookstoreUser, Byte rate) {
        this.book = book;
        this.bookstoreUser = bookstoreUser;
        this.rate = rate;
    }
}
