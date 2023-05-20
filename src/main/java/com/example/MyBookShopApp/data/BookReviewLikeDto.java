package com.example.MyBookShopApp.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookReviewLikeDto {

    private Integer id;
    private BookReviewDto bookReviewDto;
    private Byte value;
    private Integer bookReviewLikesCount;
    private Integer bookReviewDislikesCount;

    public BookReviewLikeDto(BookReviewDto bookReviewDto, Byte value) {
        this.bookReviewDto = bookReviewDto;
        this.value = value;
    }
}
