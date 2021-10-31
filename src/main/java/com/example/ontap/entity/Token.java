package com.example.ontap.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@Setter
@Getter
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    private Date expiration;
}
