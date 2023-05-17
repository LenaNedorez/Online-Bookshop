package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.BookBrowsing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookBrowsingRepository extends JpaRepository<BookBrowsing, Long> {

    List<BookBrowsing> findAll();

    List<BookBrowsing> findAllByBookStoreUser_Id(Integer id);
}
