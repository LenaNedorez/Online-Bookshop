package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.security.BookstoreUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookReviewDto {

    private Integer id;
    private LocalDateTime dateTime;
    private Book book;
    private BookstoreUser bookstoreUser;
    private String text;

}
