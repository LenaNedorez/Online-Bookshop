package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.security.BookstoreUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;


@Entity
@Table(name = "book_rate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private BookstoreUser bookstoreUser;

    @Min(1)
    @Max(5)
    private Byte rate;

}
