package com.example.ontap.dto;

import com.example.ontap.entity.Student;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class StudentDepartmentDTO {
    private Student student;
    private Department department;
}
