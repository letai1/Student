package com.example.ontap.service;

import com.example.ontap.entity.Account;
import com.example.ontap.entity.Token;

import java.util.Date;

public interface TokenInterface {
    public String TokenName(Account account);
    public Account getAccount(String username);
    public Boolean AddToken(Token token);
    public Date ExpirationTime();
}
