package com.example.ontap.service;

import com.example.ontap.dto.StudentDepartmentDTO;
import com.example.ontap.entity.Student;

import java.util.List;

public interface ServiceInterface {
    public List<Student> getList();
    public Student getStudent(int id);
    public Boolean deleteStudent(int id);
    public Boolean updateStudent(Student student);
    public Boolean addStudent(Student student);
    public StudentDepartmentDTO getStudentAndDepartment(int id);

}
