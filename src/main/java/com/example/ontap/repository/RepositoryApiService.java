package com.example.ontap.repository;

import com.example.ontap.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryApiService extends JpaRepository<Student,Integer> {
}
