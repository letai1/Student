package com.example.ontap.service;

import com.example.ontap.dto.Department;
import com.example.ontap.dto.StudentDepartmentDTO;
import com.example.ontap.entity.Student;
import com.example.ontap.repository.RepositoryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class StudentService implements ServiceInterface{

    @Autowired
    RepositoryApiService repositoryApiService;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Student> getList() {
        return repositoryApiService.findAll();
    }

    @Override
    public Student getStudent(int id) {
        return repositoryApiService.findById(id).get();
    }

    @Override
    public Boolean deleteStudent(int id) {
        Student student = getStudent(id);
        if (student!=null){
            repositoryApiService.delete(student);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateStudent(Student student) {
        if (student!=null){
            repositoryApiService.save(student);
            return true;
        }
        return false;

    }

    @Override
    public Boolean addStudent(Student student) {
        if (student!=null){
            repositoryApiService.saveAndFlush(student);
            return true;
        }
        return false;
    }

    @Override
    public StudentDepartmentDTO getStudentAndDepartment(int id) {
        Student student = getStudent(id);
        int idDepartment = student.getDepartmentId();
        Department department = restTemplate.getForObject("http://localhost:9001/department/"+idDepartment,Department.class);
        StudentDepartmentDTO dto = new StudentDepartmentDTO();
        dto.setStudent(student);
        dto.setDepartment(department);
        return dto;
    }
}
