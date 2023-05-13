package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Integer> {

    Optional<PaymentTransaction> findById(Integer id);

    List<PaymentTransaction> findAllByBookstoreUser_Id(Integer id);

    List<PaymentTransaction> findAll();
}
