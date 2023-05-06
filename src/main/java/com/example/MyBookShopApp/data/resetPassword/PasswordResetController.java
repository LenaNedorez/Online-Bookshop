package com.example.MyBookShopApp.data.resetPassword;

import com.example.MyBookShopApp.errs.InvalidPasswordTokenException;
import com.example.MyBookShopApp.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
public class PasswordResetController {

    private BookstoreUserDetailsService bookstoreUserDetailsService;
    private PasswordResetService passwordResetService;
    private final JavaMailSender javaMailSender;
    private BookstoreUserRegister bookstoreUserRegister;

    @Autowired
    public PasswordResetController(BookstoreUserDetailsService bookstoreUserDetailsService, PasswordResetService passwordResetService, JavaMailSender javaMailSender, BookstoreUserRegister bookstoreUserRegister) {
        this.bookstoreUserDetailsService = bookstoreUserDetailsService;
        this.passwordResetService = passwordResetService;
        this.javaMailSender = javaMailSender;
        this.bookstoreUserRegister = bookstoreUserRegister;
    }

    @PostMapping("/user/resetPassword")
    @ResponseBody
    public ContactConfirmationResponse resetPassword(@RequestBody ContactConfirmationPayload payload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        BookstoreUser bookstoreUser = bookstoreUserDetailsService.findUserByUsername(payload.getContact());

        String token = UUID.randomUUID().toString();
        passwordResetService.createPasswordResetTokenForUser(bookstoreUser, token, 300);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bookstore00@mail.ru");
        message.setTo(payload.getContact());
        message.setSubject("Bookstore reset password");
        message.setText("In order to reset your password, click the link:"
                        + "localhost:8085/user/changePassword?token=" + token);
        javaMailSender.send(message);
        response.setResult("true");
        return response;
    }

    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(Model model,
                                         @RequestParam("token") String token) throws InvalidPasswordTokenException {
        String result = passwordResetService.validatePasswordResetToken(token);
        if(result != null) {
            throw new InvalidPasswordTokenException("There's an error occured. To change your " +
                                                    "password, please, try again");
        } else {
            model.addAttribute("token", token);
            return "redirect:/updatePassword.html";
        }
    }

    @PostMapping("/user/savePassword")
    public ContactConfirmationResponse savePassword(PasswordResetDto passwordDto){
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        String result = passwordResetService.validatePasswordResetToken(passwordDto.getToken());

        if(result != null) {
            response.setResult("false");
            return response;
        }

        Optional bookstoreUser = passwordResetService.getUserByPasswordResetToken(passwordDto.getToken());
        if(bookstoreUser.isPresent()) {
            bookstoreUserRegister.changeUserPassword((BookstoreUser) bookstoreUser.get(), passwordDto.getNewPassword());
            response.setResult("true");
        } else {
            response.setResult("false");
        }
        return response;
    }
}
