package com.example.demo.common.Repository;

import com.example.demo.common.DTO.StudentRequest;
import com.example.demo.common.DTO.StudentResponse;
import com.example.demo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudents();

    List<Student> findAllStudent();

    Optional<Student> findStudentById(Long id);
    StudentResponse saveStudent(StudentRequest request);
    void deleteStudent(Long id);
    //业务查询
    List<Student> findStudentByClass(String className);
    List<Student> searchStudent(String name);
    List<Student> findTopStudents(Integer minScore);
    boolean isStudentNoExtists(String studentNo);
    long countStudentsByClass(String className);
    Double getAverageScoreByClass(String className);
    StudentResponse updateStudentsInfo(Long id, StudentRequest request);


    List<Student> searchStudentByName(String name);

    StudentResponse addStudent(StudentRequest request);
    Page<StudentResponse> listStudents(Pageable pageable);
    StudentResponse getStudentById(Long id);
    StudentResponse updateStudentInfo(Long id, StudentRequest request);

}
