package com.example.MyBookShopApp.data.resetPassword;

import com.example.MyBookShopApp.security.BookstoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasswordResetService {

    private PasswordResetRepository passwordResetRepository;

    @Autowired
    public PasswordResetService(PasswordResetRepository passwordResetRepository) {
        this.passwordResetRepository = passwordResetRepository;
    }

    public void createPasswordResetTokenForUser(BookstoreUser bookstoreUser, String token, Integer expiryTime) {
        PasswordResetToken myToken = new PasswordResetToken(bookstoreUser, token, expiryTime);
        passwordResetRepository.save(myToken);
    }

    public String validatePasswordResetToken(String token){
        final PasswordResetToken passToken = passwordResetRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        return passToken.getExpiryTime().isBefore(LocalDateTime.now());
    }

    public Optional getUserByPasswordResetToken(String token) {
        BookstoreUser bookstoreUser = passwordResetRepository.getBookstoreUserByToken(token);
        return Optional.of(bookstoreUser);
    }

}
