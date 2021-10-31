package com.example.ontap.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Account {
    @Id
    private String username;
    private String password;
}
