package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    Optional<Purchase> findById(Integer integer);

    List<Purchase> findAllByBookstoreUser_Id(Integer id);
}
