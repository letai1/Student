package com.example.ontap.controller;

import com.example.ontap.dto.StudentDepartmentDTO;
import com.example.ontap.entity.Account;
import com.example.ontap.entity.Student;
import com.example.ontap.entity.Token;
import com.example.ontap.service.ServiceInterface;
import com.example.ontap.service.TokenInterface;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    ServiceInterface serviceInterface;

    @Autowired
    TokenInterface tokenInterface;

    @RateLimiter(name="basicexample")
    @Cacheable(value="studentGetList")
    @GetMapping("/student")
    public List<Student> getList(){
        return serviceInterface.getList();
    }

    @GetMapping("/login")
    public ResponseEntity<?> Login(@RequestBody Account account){
        String tokenName="";
        Account acc = tokenInterface.getAccount(account.getUsername());
        if(acc == null || new BCryptPasswordEncoder().matches(account.getPassword(), acc.getPassword())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password Not True!");
        }
        //b2: save token into database
        tokenName = tokenInterface.TokenName(acc);

        Token token = new Token();
        token.setToken(tokenName);
        token.setExpiration(tokenInterface.ExpirationTime());
        tokenInterface.AddToken(token);

        return ResponseEntity.ok(tokenName);
    }

    @GetMapping("/student/{id}")
    public StudentDepartmentDTO getStudent(@PathVariable int id){
        return serviceInterface.getStudentAndDepartment(id);
    }

    @Cacheable(value="studentPut")
    @RateLimiter(name="advancedexample")
    @PutMapping("/student")
    public String updateStudent(@RequestBody Student student){
        if(serviceInterface.updateStudent(student)){
            return "Update SuccessFul!";
        }
        return "Update Not SuccessFul!";
    }

    @Cacheable(value="studentDelete")
    @Retry(name="multiplyretrywait")
    @RateLimiter(name="multiplylimittimeout")
    @DeleteMapping("/student/{id}")
    public  String deleteStudent(@PathVariable int id){
        if(serviceInterface.deleteStudent(id)){
            return "Delete SuccessFul!";
        }
        return "Delete Not SuccessFul!";
    }

    @Cacheable(value="studentPost")
    @RateLimiter(name="multiplylimit")
    @PostMapping("/student")
    public String addStudent(@RequestBody Student student){
        student.setId(0);
        if(serviceInterface.addStudent(student)){
            return "Add SuccessFul!";
        }
        return "Add Not SuccessFul!";
    }

}
