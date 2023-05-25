package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.PaymentTransaction;
import com.example.MyBookShopApp.security.BookstoreUser;
import com.example.MyBookShopApp.security.BookstoreUserRegister;
import com.example.MyBookShopApp.services.PaymentTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/payment_transactions")
public class PaymentTransactionController {

    private final PaymentTransactionService paymentTransactionService;
    private final BookstoreUserRegister bookstoreUserRegister;

    @Autowired
    public PaymentTransactionController(PaymentTransactionService paymentTransactionService, BookstoreUserRegister bookstoreUserRegister) {
        this.paymentTransactionService = paymentTransactionService;
        this.bookstoreUserRegister = bookstoreUserRegister;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<PaymentTransaction>> getAllTransactions(){
        return ResponseEntity.ok(paymentTransactionService.getAllTransactions());
    }

    @PostMapping("/user_transaction_history")
    @ResponseBody
    public ResponseEntity<List<PaymentTransaction>> getAllUserTransactions(){
        BookstoreUser bookstoreUser = (BookstoreUser) bookstoreUserRegister.getCurrentUser();
        return ResponseEntity.ok(paymentTransactionService.getAllUserTransactions(bookstoreUser.getId()));
    }

    @PostMapping("/user_transaction_history/transaction")
    @ResponseBody
    public ResponseEntity<PaymentTransaction> getCertainTransaction(@RequestParam("transaction_id") Integer id){
        return ResponseEntity.ok(paymentTransactionService.getPaymentTransactionById(id));
    }

    @Transactional
    @PostMapping("/transaction")
    public RedirectView handlePaymentTransaction(@RequestParam("payment_amount") Double paymentAmount) throws NoSuchAlgorithmException {
        BookstoreUser bookstoreUser = (BookstoreUser) bookstoreUserRegister.getCurrentUser();
        String paymentUrl = paymentTransactionService.handlePaymentTransaction(bookstoreUser.getId(), paymentAmount);
        return new RedirectView(paymentUrl);
    }
}
