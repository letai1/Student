package com.example.ontap.repository;

import com.example.ontap.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAccount extends JpaRepository<Account,String> {
}
