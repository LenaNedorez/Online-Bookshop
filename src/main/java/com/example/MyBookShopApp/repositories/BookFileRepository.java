package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFileRepository extends JpaRepository<BookFile, Integer> {

    public BookFile findBookFileByHash(String hash);
}
