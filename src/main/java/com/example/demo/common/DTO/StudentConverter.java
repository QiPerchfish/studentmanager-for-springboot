package com.example.demo.common.DTO;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Student;

@Component
public class StudentConverter {
    public Student toEntity(StudentRequest request) {
        if (request == null) return null;
        Student student = new Student();
        student.setName(request.getName());
        student.setStudentNo(request.getNumber());
        student.setAge(request.getAge());
        return student;
    }

    public StudentResponse toResponse(Student student) {
        if (student == null) return null;
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setNumber(student.getStudentNo());
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setAge(student.getAge());
        return studentResponse;
    }
}
