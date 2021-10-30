package com.example.ontap.controller;

import com.example.ontap.dto.StudentDepartmentDTO;
import com.example.ontap.entity.Student;
import com.example.ontap.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    ServiceInterface serviceInterface;

    @GetMapping("/student")
    public List<Student> getList(){
        return serviceInterface.getList();
    }

    @GetMapping("/student/{id}")
    public StudentDepartmentDTO getStudent(@PathVariable int id){
        return serviceInterface.getStudentAndDepartment(id);
    }

    @PutMapping("/student")
    public String updateStudent(@RequestBody Student student){
        if(serviceInterface.updateStudent(student)){
            return "Update SuccessFul!";
        }
        return "Update Not SuccessFul!";
    }

    @DeleteMapping("/student/{id}")
    public  String deleteStudent(@PathVariable int id){
        if(serviceInterface.deleteStudent(id)){
            return "Delete SuccessFul!";
        }
        return "Delete Not SuccessFul!";
    }

    @PostMapping("/student")
    public String addStudent(@RequestBody Student student){
        student.setId(0);
        if(serviceInterface.addStudent(student)){
            return "Add SuccessFul!";
        }
        return "Add Not SuccessFul!";
    }

}
