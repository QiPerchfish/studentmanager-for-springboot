package com.example.demo.common.Repository;

import com.example.demo.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudents();

    List<Student> findAllStudent();

    Optional<Student> findStudentById(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    //业务查询
    List<Student> findStudentByClass(String className);
    List<Student> searchStudent(String name);
    List<Student> findTopStudents(Integer minScore);
    boolean isStudentNoExtists(String studentNo);
    long countStudentsByClass(String className);
    Double getAverageScoreByClass(String className);

    List<Student> searchStudentByName(String name);
}
