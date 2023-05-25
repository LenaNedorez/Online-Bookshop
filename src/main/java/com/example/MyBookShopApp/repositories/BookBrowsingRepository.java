package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookBrowsing;
import com.example.MyBookShopApp.security.BookstoreUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookBrowsingRepository extends JpaRepository<BookBrowsing, Long> {

    List<BookBrowsing> findAll();

    List<BookBrowsing> findAllByBookstoreUser_Id(Integer id);

    @Query(value = "SELECT DENSE_RANK() OVER(ORDER BY book_browsing.book DESC) AS book_rank FROM book_browsing WHERE book_rank <= 10", nativeQuery = true)
    List<Book> getPopularBooks();

    List<BookBrowsing> findFirst10ByBookstoreUser(BookstoreUser bookstoreUser, Sort sort);

}