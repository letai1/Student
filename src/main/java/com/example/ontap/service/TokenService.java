package com.example.ontap.service;

import com.example.ontap.entity.Account;
import com.example.ontap.entity.Token;
import com.example.ontap.repository.RepositoryAccount;
import com.example.ontap.repository.RepositoryToken;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService implements TokenInterface{

    private Logger logger = LoggerFactory.getLogger(TokenService.class);
    private static final String NAME= "TAI";
    private static final String SECRET = "Hello Mr Tai has new JWSHeader with SH256";

    @Autowired
    RepositoryToken repositoryToken;

    @Autowired
    RepositoryAccount repositoryAccount;

    @Override
    public String TokenName(Account account) {
        String tokenName="";
        try {

            //b1: create JWTCLaimsSet
            JWTClaimsSet.Builder jwtClaimsSet = new JWTClaimsSet.Builder();
            jwtClaimsSet.claim(NAME,account);
            jwtClaimsSet.expirationTime(ExpirationTime());
            JWTClaimsSet claim = jwtClaimsSet.build();
            //b2: SignedJWT
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256),claim);
            signedJWT.sign(new MACSigner(SECRET.getBytes()));

            //b3: serializable()
            tokenName = signedJWT.serialize();

        } catch (Exception e){
            logger.error(e.getMessage());
        }

        return tokenName;
    }


    @Override
    public Account getAccount(String username) {
        return repositoryAccount.findById(username).get();
    }

    @Override
    public Boolean AddToken(Token token) {
        if(!token.getToken().equals("")){
            repositoryToken.saveAndFlush(token);
            return true;
        }
        return false;
    }

    @Override
    public Date ExpirationTime() {
        return new Date(System.currentTimeMillis() + 86400000);
    }
}
