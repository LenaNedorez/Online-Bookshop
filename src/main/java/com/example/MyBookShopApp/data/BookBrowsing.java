package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.security.BookstoreUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_browsings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookBrowsing {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "books_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private BookstoreUser bookstoreUser;

    private LocalDateTime dateTime;

    public BookBrowsing(Book book, BookstoreUser bookstoreUser, LocalDateTime dateTime) {
        this.book = book;
        this.bookstoreUser = bookstoreUser;
        this.dateTime = dateTime;
    }
}
