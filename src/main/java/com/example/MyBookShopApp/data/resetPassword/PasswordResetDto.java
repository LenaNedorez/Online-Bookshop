package com.example.MyBookShopApp.data.resetPassword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetDto {

    private String oldPassword;

    private String token;

    private String newPassword;
}
