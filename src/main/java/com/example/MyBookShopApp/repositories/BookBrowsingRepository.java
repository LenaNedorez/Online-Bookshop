package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.BookBrowsing;
import com.example.MyBookShopApp.security.BookstoreUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookBrowsingRepository extends JpaRepository<BookBrowsing, Long> {

    List<BookBrowsing> findAll();

    List<BookBrowsing> findAllByBookstoreUser_Id(Integer id);

    List<BookBrowsing> findFirst10ByBookstoreUser(BookstoreUser bookstoreUser, Sort sort);

    List<BookBrowsing> findAllByDateTimeAfter(LocalDateTime dateTime);
}