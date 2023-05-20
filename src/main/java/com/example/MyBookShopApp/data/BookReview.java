package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.security.BookstoreUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_review")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private BookstoreUser bookstoreUser;

    private String text;

    public BookReview(LocalDateTime dateTime, Book book, BookstoreUser bookstoreUser, String text) {
        this.dateTime = dateTime;
        this.book = book;
        this.bookstoreUser = bookstoreUser;
        this.text = text;
    }
}
