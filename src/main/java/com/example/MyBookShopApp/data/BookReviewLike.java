package com.example.MyBookShopApp.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book_review_like")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_review_id", referencedColumnName = "id")
    private BookReview bookReview;

    private Byte value;

    private Integer bookReviewLikesCount;
    private Integer bookReviewDislikesCount;

}