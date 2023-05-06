package com.example.MyBookShopApp.data.resetPassword;

import com.example.MyBookShopApp.security.BookstoreUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Integer> {

    PasswordResetToken findByToken(String token);

    BookstoreUser getBookstoreUserByToken(String token);
}
